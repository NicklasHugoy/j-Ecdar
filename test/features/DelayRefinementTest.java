package features;

import logic.Composition;
import logic.Refinement;
import logic.SimpleTransitionSystem;
import logic.TransitionSystem;
import models.Automaton;
import models.CDD;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import parser.XMLFileWriter;
import parser.XMLParser;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class DelayRefinementTest {

    private static Automaton[] automata;


    @After
    public void afterEachTest(){
        CDD.done();
    }
//Class

    @Before
    public  void setUpBeforeClass() {
        automata = XMLParser.parse("./samples/xml/delayRefinement.xml", true);
    }

    // Self Refinements
    @Test
    public void T1RefinesSelf() {

        Automaton automaton2 = new Automaton(automata[0]);
        assertTrue(new Refinement(new SimpleTransitionSystem((automata[0])), new SimpleTransitionSystem((automaton2))).check());
    }

    @Test
    public void T2RefinesSelf() {
        Automaton automaton2 = new Automaton(automata[1]);
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[1]), new SimpleTransitionSystem(automaton2)).check());
    }

    @Test
    public void T3RefinesSelf() {

        Automaton automaton2 = new Automaton(automata[2]);
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[2]), new SimpleTransitionSystem(automaton2)).check());
    }

    @Test
    public void C1RefinesSelf() {

        Automaton automata2 = new Automaton(automata[3]);
        assertTrue(new Refinement(new SimpleTransitionSystem((automata[3])), new SimpleTransitionSystem(((automata2)))).check());
    }

    @Test
    public void C2RefinesSelf() {

        Automaton automata2 = new Automaton(automata[4]);
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[4]), new SimpleTransitionSystem(automata2)).check());
    }

    @Test
    public void F1RefinesSelf() {

        Automaton automata2 = new Automaton(automata[7]);
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[7]), new SimpleTransitionSystem(automata2)).check());
    }

    @Test
    public void F2RefinesSelf() {
        Automaton automata2 = new Automaton(automata[8]);
        assertTrue(new Refinement(new SimpleTransitionSystem((automata[8])), new SimpleTransitionSystem((automata2))).check());
    }

    @Test
    public void F3RefinesSelf() {
        Automaton automata2 = new Automaton(automata[9]);
        assertTrue(new Refinement(new SimpleTransitionSystem((automata[9])), new SimpleTransitionSystem((automata2))).check());
    }

    @Test
    public void T4RefinesSelf() {

        Automaton automata2 = new Automaton(automata[10]);
        assertTrue(new Refinement(new SimpleTransitionSystem((automata[10])), new SimpleTransitionSystem((automata2))).check());
    }

    @Test
    public void T0RefinesSelf() {

        assertTrue(new Refinement(new SimpleTransitionSystem(automata[11]), new SimpleTransitionSystem(new Automaton(automata[11]))).check());
    }

    @Test
    public void T5RefinesSelf() {

        Automaton automata2 = new Automaton(automata[12]);
        assertTrue(new Refinement(new SimpleTransitionSystem((automata[12])), new SimpleTransitionSystem((automata2))).check());
    }

    @Test
    public void T6RefinesSelf() {

        Automaton automata2 = new Automaton(automata[13]);
        assertTrue(new Refinement(new SimpleTransitionSystem((automata[13])), new SimpleTransitionSystem((automata2))).check());
    }

    @Test
    public void T7RefinesSelf() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[14]), new SimpleTransitionSystem(new Automaton(automata[14]))).check());
    }

    @Test
    public void T8RefinesSelf() {
        Automaton automata2 = new Automaton(automata[15]);
        assertTrue(new Refinement(new SimpleTransitionSystem((automata[15])), new SimpleTransitionSystem((automata2))).check());
    }

    @Test
    public void T9RefinesSelf() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[16]), new SimpleTransitionSystem(new Automaton(automata[16]))).check());
    }

    @Test
    public void T10RefinesSelf() {
        Automaton automata2 = new Automaton(automata[17]);
        assertTrue(new Refinement(new SimpleTransitionSystem((automata[17])), new SimpleTransitionSystem((automata2))).check());
    }

    @Test
    public void T11RefinesSelf() {
        Automaton automata2 = new Automaton(automata[18]);
        assertTrue(new Refinement(new SimpleTransitionSystem((automata[18])), new SimpleTransitionSystem((automata2))).check());
    }

    @Test
    public void N1RefinesSelf() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[19]), new SimpleTransitionSystem(new Automaton(automata[19]))).check());
    }

    @Test
    public void N2RefinesSelf() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[20]), new SimpleTransitionSystem(new Automaton(automata[20]))).check());
    }

    @Test
    public void N3RefinesSelf() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[21]), new SimpleTransitionSystem(new Automaton(automata[21]))).check());
    }

    @Test
    public void N4RefinesSelf() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[22]), new SimpleTransitionSystem(new Automaton(automata[22]))).check());
    }

    @Test
    public void D1RefinesSelf() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[23]), new SimpleTransitionSystem(new Automaton(automata[23]))).check());
    }

    @Test
    public void D2RefinesSelf() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[24]), new SimpleTransitionSystem(new Automaton(automata[24]))).check());
    }

    @Test
    public void K1RefinesSelf() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[25]), new SimpleTransitionSystem(new Automaton(automata[25]))).check());
    }

    @Test
    public void K2RefinesSelf() {

        Automaton automata2 = new Automaton(automata[26]);
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[26]), new SimpleTransitionSystem((automata2))).check());
    }

    @Test
    public void K3RefinesSelf() {

        Automaton automata2 = new Automaton(automata[27]);
        assertTrue(new Refinement(new SimpleTransitionSystem((automata[27])), new SimpleTransitionSystem((automata2))).check());
    }

    @Test
    public void K4RefinesSelf() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[28]), new SimpleTransitionSystem(new Automaton(automata[28]))).check());
    }

    @Test
    public void K5RefinesSelf() {

        Automaton automata2 = new Automaton(automata[29]);
        assertTrue(new Refinement(new SimpleTransitionSystem((automata[29])), new SimpleTransitionSystem((automata2))).check());
    }

    @Test
    public void K6RefinesSelf() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[30]), new SimpleTransitionSystem(new Automaton(automata[30]))).check());
    }

    @Test
    public void P0RefinesSelf() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[31]), new SimpleTransitionSystem(new Automaton(automata[31]))).check());
    }

    @Test
    public void P1RefinesSelf() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[32]), new SimpleTransitionSystem(new Automaton(automata[32]))).check());
    }

    @Test
    public void P2RefinesSelf() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[33]), new SimpleTransitionSystem(new Automaton(automata[33]))).check());
    }

    @Test
    public void P3RefinesSelf() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[34]), new SimpleTransitionSystem(new Automaton(automata[34]))).check());
    }

    @Test
    public void P4RefinesSelf() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[35]), new SimpleTransitionSystem(new Automaton(automata[35]))).check());
    }

    @Test
    public void P5RefinesSelf() {
        Automaton automata2 = new Automaton(automata[36]);
        //assertTrue(new SimpleTransitionSystem(automata[36]).isDeterministic());
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[36]), new SimpleTransitionSystem(automata2)).check());
    }

    @Test
    public void P6RefinesSelf() {
        Automaton automata2 = new Automaton(automata[37]);
        assertTrue(new Refinement(new SimpleTransitionSystem((automata[37])), new SimpleTransitionSystem(((automata2)))).check());
    }

    @Test
    public void P7RefinesSelf() {
        Automaton automata2 = new Automaton(automata[38]);
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[38]), new SimpleTransitionSystem(automata2)).check());
    }

    @Test
    public void L1RefinesSelf() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[39]), new SimpleTransitionSystem(new Automaton(automata[39]))).check());
    }

    @Test
    public void L2RefinesSelf() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[40]), new SimpleTransitionSystem(new Automaton(automata[40]))).check());
    }

    @Test
    public void L3RefinesSelf() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[41]), new SimpleTransitionSystem(new Automaton(automata[41]))).check());
    }

    @Test
    public void L4RefinesSelf() {
        Automaton automata2 = new Automaton(automata[42]);
        assertTrue(new Refinement(new SimpleTransitionSystem((automata[42])), new SimpleTransitionSystem((automata2))).check());
    }

    @Test
    public void L5RefinesSelf() {

        Automaton automata2 = new Automaton(automata[43]);
        assertTrue(new Refinement(new SimpleTransitionSystem((automata[43])), new SimpleTransitionSystem((automata2))).check());
    }

    @Test
    public void L6RefinesSelf() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[44]), new SimpleTransitionSystem(new Automaton(automata[44]))).check());
    }

    @Test
    public void L7RefinesSelf() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[45]), new SimpleTransitionSystem(new Automaton(automata[45]))).check());
    }

    @Test
    public void Z1RefinesSelf() {
        Automaton automata2 = new Automaton(automata[46]);
        assertTrue(new Refinement(new SimpleTransitionSystem((automata[46])), new SimpleTransitionSystem((automata2))).check());
    }

    @Test
    public void Z2RefinesSelf() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[47]), new SimpleTransitionSystem(new Automaton(automata[47]))).check());
    }

    @Test
    public void Z3RefinesSelf() {

        Automaton automata2 = new Automaton(automata[48]);
        assertTrue(new Refinement(new SimpleTransitionSystem((automata[48])), new SimpleTransitionSystem((automata2))).check());
    }

    @Test
    public void Z4RefinesSelf() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[49]), new SimpleTransitionSystem(new Automaton(automata[49]))).check());
    }

    @Test
    public void Z5RefinesSelf() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[50]), new SimpleTransitionSystem(new Automaton(automata[50]))).check());
    }

    @Test
    public void Z6RefinesSelf() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[51]), new SimpleTransitionSystem(new Automaton(automata[51]))).check());
    }

    @Test
    public void Z7RefinesSelf() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[52]), new SimpleTransitionSystem(new Automaton(automata[52]))).check());
    }

    // Rest of the tests


    @Test
    public void T2isImplementation() {
        assertTrue(new SimpleTransitionSystem((automata[1])).isDeterministic());
        assertTrue(new SimpleTransitionSystem((automata[1])).isFullyConsistent());
    }



    @Test
    public void T1T2RefinesT3() { // TODO: T2 is not consistent...... see test before
        TransitionSystem comp = new Composition(
                new TransitionSystem[]{
                        new SimpleTransitionSystem((automata[0])),
                        new SimpleTransitionSystem((automata[1]))});
       Automaton array[] = new Automaton[]{comp.getAutomaton()};
        XMLFileWriter.toXML("testOutput/compT1T2.xml", array);
        assertTrue(new Refinement(comp, new SimpleTransitionSystem((automata[2]))).check());
    }
/*
    @Test
    public void T12RefinesT3() { // never finished the test
        CDD.init(1000,1000,1000);
        CDD.addClocks(automata[55].getClocks(),automata[2].getClocks());
        assertTrue(new Refinement(new SimpleTransitionSystem((automata[55])), new SimpleTransitionSystem((automata[2]))).check());
    }*/


    @Test
    public void C1RefinesC2() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[3]), new SimpleTransitionSystem(automata[4])).check());
    }

    @Test
    public void C2RefinesC1() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[4]), new SimpleTransitionSystem(automata[3])).check());
    }

    @Test
    public void T0T1T2RefinesT3() {
        TransitionSystem comp = new Composition(
                new TransitionSystem[]{
                        new SimpleTransitionSystem(automata[11]),
                        new SimpleTransitionSystem(automata[0]),
                        new SimpleTransitionSystem(automata[1])});
        assertTrue(new Refinement(comp, new SimpleTransitionSystem(automata[2])).check());
    }

    @Test
    public void F1F2RefinesF3() {


        TransitionSystem comp = new Composition(
                new TransitionSystem[]{
                        new SimpleTransitionSystem(automata[7]),
                        new SimpleTransitionSystem(automata[8])});
        Refinement ref = new Refinement(comp, new SimpleTransitionSystem(automata[9]));
        boolean result =ref.check();
        //System.out.println(comp.isImplementation());
        System.out.println(ref.getErrMsg());


        assertTrue(result);
    }

    @Test
    public void T4RefinesT3() { // TODO: T4 has no independent progress
        //assertTrue(new SimpleTransitionSystem((automata[10])).isFullyConsistent());
        //assertTrue(new SimpleTransitionSystem((automata[2])).isFullyConsistent());
       // assertTrue(new SimpleTransitionSystem((automata[10])).isImplementation());

        assertTrue(new Refinement(new SimpleTransitionSystem((automata[10])), new SimpleTransitionSystem((automata[2]))).check());
    }

    @Test
    public void T6RefinesT5() {
        assertTrue(new Refinement(new SimpleTransitionSystem((automata[13])), new SimpleTransitionSystem((automata[12]))).check(true));
    }

    @Test
    public void T7NotRefinesT8() {
        // should fail because left side has more inputs
        assertFalse(new Refinement(new SimpleTransitionSystem(automata[14]), new SimpleTransitionSystem(automata[15])).check());
    }

    @Test
    public void T9NotRefinesT8() {
        assertFalse(new Refinement(new SimpleTransitionSystem((automata[16])), new SimpleTransitionSystem((automata[15]))).check());
    }

    @Test
    public void T10NotRefinesT11() {
        // should fail because left side has more inputs
        //assertFalse(new Refinement(new SimpleTransitionSystem((automata[17])), new SimpleTransitionSystem((automata[18]))).check());
    }

    @Test
    public void N1RefinesN2() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[19]), new SimpleTransitionSystem(automata[20])).check());
    }

    @Test
    public void N3RefinesN4() {
        // should fail because right side has more inputs
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[21]), new SimpleTransitionSystem(automata[22])).check());
    }

    @Test
    public void D2RefinesD1() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[24]), new SimpleTransitionSystem(automata[23])).check());
    }

    @Test
    public void D1NotRefinesD2() {
        // should fail because right side has more outputs
        assertFalse(new Refinement(new SimpleTransitionSystem(automata[23]), new SimpleTransitionSystem(automata[24])).check());
    }

    @Test
    public void K1NotRefinesK2() {
        assertFalse(new Refinement(new SimpleTransitionSystem(automata[25]), new SimpleTransitionSystem(automata[26])).check());
    }

    @Test
    public void K3NotRefinesK4() {
        assertFalse(new Refinement(new SimpleTransitionSystem((automata[27])), new SimpleTransitionSystem((automata[28]))).check());
    }

    @Test
    public void K5NotRefinesK6() {
        assertFalse(new Refinement(new SimpleTransitionSystem(automata[29]), new SimpleTransitionSystem(automata[30])).check());
    }

    @Test
    public void P0RefinesP1() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[31]), new SimpleTransitionSystem(automata[32])).check());
    }

    @Test
    public void P2NotRefinesP3() {
        assertFalse(new Refinement(new SimpleTransitionSystem(automata[33]), new SimpleTransitionSystem(automata[34])).check());
    }

    @Test
    public void P4RefinesP5() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[35]), new SimpleTransitionSystem(automata[36])).check());
    }

    @Test
    public void P6RefinesP7() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[37]), new SimpleTransitionSystem(automata[38])).check());
    }

    @Test
    public void L1L2NotRefinesL3(){
        TransitionSystem comp = new Composition(
                new TransitionSystem[]{
                        new SimpleTransitionSystem((automata[39])),
                        new SimpleTransitionSystem((automata[40]))});
        boolean result = new Refinement(comp, new SimpleTransitionSystem((automata[41]))).check();
        System.out.println(result);
        assertFalse(result);
    }

    @Test
    public void L4RefinesL5() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[42]), new SimpleTransitionSystem(automata[43])).check());
    }

    @Test
    public void L6RefinesL7() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[44]), new SimpleTransitionSystem(automata[45])).check());
    }

    @Test
    public void Z1RefinesZ2() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[46]), new SimpleTransitionSystem(automata[47])).check());
    }

    @Test
    public void Z3RefinesZ4() {
        assertTrue(new Refinement(new SimpleTransitionSystem(automata[48]), new SimpleTransitionSystem(automata[49])).check());
    }

    @Test
    public void Z5Z6NotRefinesZ7() {
        TransitionSystem comp = new Composition(
                new TransitionSystem[]{
                        new SimpleTransitionSystem(automata[50]),
                        new SimpleTransitionSystem(automata[51])});
        assertFalse(new Refinement(comp, new SimpleTransitionSystem(automata[52])).check());
    }

    @Test
    public void Q1RefinesQ2() {
        assertFalse(new Refinement(new SimpleTransitionSystem(automata[53]), new SimpleTransitionSystem(automata[54])).check());
    }

    @Test
    public void Q2RefinesQ1() {
        assertFalse(new Refinement(new SimpleTransitionSystem(automata[54]), new SimpleTransitionSystem(automata[53])).check());
    }

    @Test
    public void M1RefinesM0() {
        Refinement ref = new Refinement(new SimpleTransitionSystem(automata[57]), new SimpleTransitionSystem(automata[56]));
        boolean res = ref.check();
        System.out.println(ref.getErrMsg());
        assertTrue(res);
    }

    @Test
    public void notM0RefinesM1() {
        assertFalse(new Refinement(new SimpleTransitionSystem(automata[56]), new SimpleTransitionSystem(automata[57])).check());
    }


}
