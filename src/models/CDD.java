package models;

import Exceptions.CddAlreadyRunningException;
import Exceptions.CddNotRunningException;
import lib.CDDLib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CDD {

    private long pointer;
    private static int numClocks; // includes the + 1 for initial clock
    private static boolean cddIsRunning;
    private static List<Clock> clocks = new ArrayList<>();


    public CDD(){
        this.pointer = CDDLib.allocateCdd();
    }


    public CDD(long pointer){
        this.pointer = pointer;
    }


    private static int getIndexOfClock(Clock clock) {

        for (int i = 0; i < clocks.size(); i++){
            if(clock.hashCode() == clocks.get(i).hashCode()) return i+1;
        }
        System.out.println("Clock not found: clock: " + clock + " List: " + clocks);
        return 0;
    }

    public CDD(List<List<Guard>> guards){
        CDD res = cddFalse();

        for (List<Guard> guardList: guards)
        {
            Zone z = new Zone(numClocks,true);
            z.init();
            for (Guard guard: guardList) {
                z.buildConstraintsForGuard(guard,getIndexOfClock(guard.getClock()));
            }
            res = res.disjunction(CDD.allocateFromDbm(z.getDbm(), numClocks));
        }
        this.pointer = res.pointer;
        if (guards.isEmpty())
        {

            CDD unres = getUnrestrainedCDD();

            this.pointer = unres.pointer;
        }
    }

    public static List<List<Guard>> toGuards(CDD state){
        List<List<Guard>> guards = new ArrayList<>();
        CDD copy = new CDD(state.pointer);

        while (!copy.isTerminal())
        {
            CddExtractionResult res = copy.extractBddAndDbm();
            copy = res.getCddPart();
            Zone z = new Zone(res.getDbm());
            CDD bddPart = res.getBddPart();
            List<Guard> guardList = z.buildGuardsFromZone(clocks);
            z.printDBM(false,true);
            //System.out.println(guardList);

            //guardList.add(bddPart.toGuards()); // TODO: once we have boolean
            guards.add(guardList);
        }


        return guards;
    }

    public long getPointer()
    {
        return pointer;
    }

    public static CDD cddTrue()
    {
        return new CDD(CDDLib.cddTrue());
    }

    public static CDD cddFalse()
    {
        return new CDD(CDDLib.cddFalse());
    }

    public boolean isTerminal()
    {
        return CDDLib.isTerminal(pointer);
    }

    public static int init(int maxSize, int cs, int stackSize) throws CddAlreadyRunningException {
        if(cddIsRunning){
            throw new CddAlreadyRunningException("Can't initialize when already running");
        }
        cddIsRunning = true;
        return CDDLib.cddInit(maxSize, cs, stackSize);
    }

    public boolean equiv(CDD that){
        if ((!this.conjunction(that.negation()).isValid())
            && (!that.conjunction(this.negation()).isValid()))
            return true;
        return false;
        //return CDDLib.cddEquiv(this,that);
    }

    public static void done(){
        cddIsRunning = false;
        numClocks = 0;
        clocks = new ArrayList<>();
        CDDLib.cddDone();
    }

    public static CDD zeroCDD()
    {
        Zone z = new Zone(numClocks,false);
        return CDD.allocateFromDbm(z.getDbm(),numClocks);
    }

    @SafeVarargs
    public static void addClocks(List<Clock>... clocks) throws CddNotRunningException {
        if(!cddIsRunning){
            throw new CddNotRunningException("Can't add clocks without before running CDD.init");
        }
        for (List<Clock> list: clocks)
            CDD.clocks.addAll(list);

        numClocks = CDD.clocks.size()+1;
        CDDLib.cddAddClocks(numClocks);
    }

    public static int addBddvar(int amount) { return CDDLib.addBddvar(amount); }

    public static CDD allocate(){
        return new CDD();
    }

    public static CDD allocateInterval(int i, int j, int lower, int upper){
        // TODO: Dont think this works.
        assert(false);
        return new CDD(CDDLib.interval(i,j,lower,upper));
    }

    public static CDD allocateFromDbm(int[] dbm, int dim){
        return new CDD(CDDLib.cddFromDbm(dbm, dim));
    }

    public static CDD allocateLower(int i, int j, int lowerBound) {
        return new CDD(CDDLib.lower(i,j,lowerBound));
    }

    public static CDD allocateUpper(int i, int j, int upperBound) {
        return new CDD(CDDLib.upper(i,j,upperBound));
    }

    public static CDD createBddNode(int level) {
        return new CDD(CDDLib.cddBddvar(level));
    }

    public static CDD createNegatedBddNode(int level) {
        return new CDD(CDDLib.cddNBddvar(level));
    }

    public static void free(CDD cdd){
        if(cdd.pointer == 0){
            throw new NullPointerException("CDD object is null");
        }
        CDDLib.freeCdd(cdd.pointer);
        cdd.pointer = 0;
    }

    public CDD copy(){
        checkForNull();
        return new CDD(CDDLib.copy(pointer));
    }

    public CDD delay()
    {
        checkForNull();
        return new CDD(CDDLib.delay(pointer));
    }

    public CDD delayInvar(CDD invariant)
    {
        checkForNull();
        return new CDD(CDDLib.delayInvar(pointer, invariant.pointer));
    }

    public CDD exist(int[] levels, int[] clocks){
        checkForNull();
        return new CDD(CDDLib.exist(pointer, levels, clocks));
    }

    public CDD past(){ // TODO: make sure this is used at the correct spots everywhere, might have been confuces with delay
        checkForNull();
        return new CDD(CDDLib.past(pointer));
    }

    public CDD removeNegative(){
        checkForNull();
        return new CDD(CDDLib.removeNegative(pointer));
    }

    public CDD applyReset(int[] clockResets, int[] clockValues, int[] boolResets, int[] boolValues){
        checkForNull();
        return new CDD(CDDLib.applyReset(pointer, clockResets, clockValues, boolResets, boolValues));
    }

    public CDD minus(CDD other){
        checkForNull();
        other.checkForNull();
        return new CDD(CDDLib.minus(pointer, other.pointer));
    }

    public CDD transition(CDD guard, int[] clockResets, int[] clockValues, int[] boolResets, int[] boolValues){
        checkForNull();
        guard.checkForNull();
        return new CDD(CDDLib.transition(pointer, guard.pointer, clockResets, clockValues, boolResets, boolValues));
    }

    public CDD transitionBack(CDD guard, CDD update, int[] clockResets, int[] boolResets){
        checkForNull();
        guard.checkForNull();
        update.checkForNull();
        return new CDD(CDDLib.transitionBack(pointer, guard.pointer, update.pointer, clockResets, boolResets));
    }

    public CDD predt(CDD safe){
        checkForNull();
        safe.checkForNull();
        return new CDD(CDDLib.predt(pointer, safe.pointer));
    }

    public CddExtractionResult extractBddAndDbm(){
        checkForNull();
        return new CddExtractionResult(CDDLib.extractBddAndDbm(pointer));
    }

    public CDD transitionBackPast(CDD guard, CDD update, int[] clockResets, int[] boolResets){
        checkForNull();
        guard.checkForNull();
        update.checkForNull();
        return new CDD(CDDLib.transitionBackPast(pointer, guard.pointer, update.pointer, clockResets, boolResets));
    }

    public CDD conjunction(CDD other){
        checkForNull();
        other.checkForNull();
        long resultPointer = CDDLib.conjunction(pointer, other.pointer);
        return new CDD(resultPointer);
    }

    public CDD disjunction(CDD other){
        checkForNull();
        other.checkForNull();
        long resultPointer = CDDLib.disjunction(pointer, other.pointer);
        return new CDD(resultPointer);
    }

    public CDD negation() {
        checkForNull();
        long resultPointer = CDDLib.negation(pointer);
        return new CDD(resultPointer);
    }

    public CDD reduce() {
        checkForNull();
        long resultPointer = CDDLib.reduce(pointer);
        return new CDD(resultPointer);
    }

    public int getNodeCount(){
        checkForNull();
        return CDDLib.cddNodeCount(pointer);
    }

    public CDDNode getRoot(){
        checkForNull();
        long nodePointer = CDDLib.getRootNode(this.pointer);
        return new CDDNode(nodePointer);
    }

    public void printDot() {
        checkForNull();
        CDDLib.cddPrintDot(pointer);
    }

    public void printDot(String filePath){
        checkForNull();
        CDDLib.cddPrintDot(pointer, filePath);
    }

    public static CDD applyReset(CDD state, Update[] updates)
    {
        int[] clockResets = new int[updates.length];
        int[] clockValues = new int[updates.length];
        int[] boolResets = {};
        int[] boolValues= {};

        return state.applyReset(clockResets,clockValues,boolResets,boolValues);
    }


    public static CDD applyReset(CDD state, List<Update> list)
    {
        int[] clockResets = new int[list.size()];
        int[] clockValues = new int[list.size()];
        int[] boolResets = {};
        int[] boolValues= {};

        return state.applyReset(clockResets,clockValues,boolResets,boolValues);
    }

    private void checkForNull(){
        if(pointer == 0){
            throw new NullPointerException("CDD object is null");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CDD cdd = (CDD) o;
        return pointer == cdd.pointer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointer);
    }


    public boolean isValid() {
        if (isTerminal())
            return false;
        else return true;
    }


    public static CDD predt(CDD A, CDD B) {
        // TODO!
        return null;
    }

    public static  boolean canDelayIndefinitely(CDD state) {
        CDD copy = new CDD(state.getPointer());
        while (!copy.isTerminal())
        {

            CddExtractionResult res = copy.extractBddAndDbm();
            Zone z = new Zone(res.getDbm());
            if (z.canDelayIndefinitely())  // TODO: is it enough if one can do it??
                return true;
        }
        assert false;
        return false;
    }

    public static  boolean isUrgent(CDD state) {
        CDD copy = new CDD(state.getPointer());
        while (!copy.isTerminal())
        {
            CddExtractionResult res = copy.extractBddAndDbm();
            Zone z = new Zone(res.getDbm());
            if (z.isUrgent())
                return true; // TODO: is it enough if one is urgent?
        }
        assert false;
        return false;
    }

    public static boolean intersects(CDD A, CDD B) {
        if (A.conjunction(B).isValid())
            return true;
        else return false;
    }


    public static boolean isSubset(CDD A, CDD B) { // A (= B
        if ((A.conjunction(B).equiv(A))) // TODO: check if correct
            return true;
        else return false;
    }

    public boolean isUnrestrained() {
        if (this.equiv(cddTrue().removeNegative())) // TODO: check if correct
            return true;
        else return false;
    }

    public static CDD getUnrestrainedCDD()
    {
        return CDD.cddTrue().removeNegative();
    }

    public  CDD transition( Edge e)
    {
        int[] clockResets = new int[e.getUpdates().length];
        int[] clockValues = new int[e.getUpdates().length];
        int[] boolResets = {};
        int[] boolValues= {};
        int i=0;
        for (Update u : e.getUpdates())
        {
            clockResets[i]=getIndexOfClock(u.getClock());
            clockValues[i]=u.getValue();
            i++;
        }

        return this.transition(e.getGuardCDD(),clockResets,clockValues,boolResets,boolValues);
    }


    public CDD transitionBack( Edge e)
    {
        int[] clockResets = new int[e.getUpdates().length];
        int[] boolResets = {};
        int i=0;
        for (Update u : e.getUpdates())
        {
            clockResets[i]=getIndexOfClock(u.getClock());

            i++;
        }
        return this.transitionBack(e.getGuardCDD(),turnUpdatesToCDD(e.getUpdates()),clockResets,boolResets);
    }


    public static CDD turnUpdatesToCDD(Update[] updates)
    {

        CDD res = cddFalse();
        for (Update u : updates)
        {
            res = res.disjunction(CDD.allocateInterval(getIndexOfClock(u.getClock()),0,u.getValue(),u.getValue()));
        }
        return res;
    }

    public static Automaton makeInputEnabled(Automaton aut) {
        Automaton copy = aut; //new Automaton(aut);
        if (clocks.size() > 0) {
            for (Location loc : copy.getLocations()) {
                CDD fullCDD = loc.getInvariantCDD();

                // loop through all inputs
                for (Channel input : copy.getInputAct()) {
                    // build CDD of zones from edges
                    List<Edge> inputEdges = copy.getEdgesFromLocationAndSignal(loc, input);

                    CDD resCDD = cddTrue();
                    CDD cddOfAllInputs = cddFalse();
                    if (!inputEdges.isEmpty()) {
                        for (Edge edge : inputEdges) {
                            CDD target = edge.getTarget().getInvariantCDD();
                            //target.printDot();
                            CDD preReset = CDD.applyReset(target, Arrays.asList(edge.getUpdates()));
                            CDD preGuard = preReset.conjunction(edge.getGuardCDD());
                            CDD preGuard1 = target.transition(edge);
                            assert (preGuard1.equiv(preGuard));
                            cddOfAllInputs = cddOfAllInputs.disjunction(preGuard);
                        }
                        //cddOfAllInputs.printDot();
                        // subtract the federation of zones from the original fed
                        resCDD = fullCDD.minus(cddOfAllInputs);
                    } else {
                        resCDD = fullCDD;
                    }
                    Edge newEdge = new Edge(loc, loc, input, true, CDD.toGuards(resCDD), new Update[]{});
                    copy.getEdges().add(newEdge);


                }
            }
        }
        return  copy;
    }

    public static Automaton addTargetInvariantToEdges(Automaton aut) {

        Automaton copy = new Automaton(aut);
        if (clocks.size() > 0) {
            for (Edge edge : copy.getEdges()) {
                CDD targetCDD = edge.getTarget().getInvariantCDD();
                CDD past = targetCDD.transitionBack(edge);
                edge.setGuards(CDD.toGuards(past));
            }
        } // TODO: else part will be important once we have bool support
        return copy;
    }



}
