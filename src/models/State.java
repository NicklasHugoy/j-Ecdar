package models;

import lib.DBMLib;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class State {
		private List<Location> locations;
		private int[] zone;
		private int zoneSize;
		private int maxBound;

		public State(List<Location> locations, int[] zone) {
				this.locations = locations;
				this.zone = zone;
				this.zoneSize = (int) Math.sqrt(zone.length);
				this.maxBound = 1073741823;

				String fileName = "src/" + System.mapLibraryName("DBM");
				File lib = new File(fileName);
				System.load(lib.getAbsolutePath());
		}

		public List<Location> getLocations() {
				return locations;
		}

		public int[] getZone() {
				return zone;
		}

		public int[] getZoneValues() {
				int[] newZone = new int[zone.length];

				for (int i = 0; i < zone.length; i++) {
						newZone[i] = DBMLib.raw2bound(zone[i]);
				}

				return newZone;
		}

		private void buildConstraintsForGuard(Guard g, List<Clock> clocks) {
				// get the guard's index in the clock array so you know the index in the DBM
				int i = clocks.indexOf(g.getClock()) + 1;

				int lowerBoundI = g.getLowerBound();
				int upperBoundI = g.getUpperBound();

				if (upperBoundI == maxBound) {
						zone = DBMLib.dbm_constrain1(zone, zoneSize, 0, i, (-1) * lowerBoundI);
				}

				if (lowerBoundI == 0) {
						zone = DBMLib.dbm_constrain1(zone, zoneSize, i, 0, upperBoundI);
				}
		}

		public int getMaxValuation() {
				int[] newZone = getZoneValues();

				for (int i = 1; i < zoneSize; i++) {
						int curr = newZone[zoneSize*i];
						if (curr < maxBound)
								maxBound = curr;
				}

				return maxBound;
		}

		public int getMinValuation() {
				int[] newZone = getZoneValues();

				int min = 0;

				for (int i = 1; i < zoneSize; i++) {
						int curr = (-1) * newZone[i];
						if (curr > min)
								min = curr;
				}

				return min;
		}

		public List<Guard> getInvariants() {
				List<Guard> invariants = new ArrayList<>();

				for (Location location : locations) {
						Guard invariant = location.getInvariant();
						if (invariant != null) invariants.add(invariant);
				}

				return invariants;
		}

		public void applyGuards(List<Guard> guards, List<Clock> clocks) {
				for (Guard guard : guards) {
						// get guard and then its index in the clock array so you know the index in the DBM
						buildConstraintsForGuard(guard, clocks);
				}
		}

		public void applyInvariants(List<Clock> clocks) {
				for (Guard invariant : getInvariants()) {
						buildConstraintsForGuard(invariant, clocks);
				}
		}

		public void applyResets(List<Update> resets, List<Clock> clocks) {
				for (Update reset : resets) {
						int index = clocks.indexOf(reset.getClock());

						zone = DBMLib.dbm_updateValue(zone, zoneSize, (index + 1), reset.getValue());
				}
		}

		public void delay() {
				zone = DBMLib.dbm_up(zone, zoneSize);
		}

		@Override
		public String toString() {
				return "State{" +
								"locations=" + locations +
								", zone=" + Arrays.toString(zone) +
								'}';
		}

		@Override
		public boolean equals(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
				State state = (State) o;
				return Arrays.equals(locations.toArray(), state.locations.toArray()) &&
								Arrays.equals(zone, state.zone);
		}

		@Override
		public int hashCode() {
				int result = Objects.hash(locations);
				result = 31 * result + Arrays.hashCode(zone);
				return result;
		}
}
