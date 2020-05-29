package ca.ciccc.wmad.kaden.oct_24th.material.src.Lessons.AUniverseExperiment;

import java.util.Random;

class Location {
    public int axisID;
    public int value;
    public int vector;


    public Location(int initialValue, int initialVector, int axisID) {
        System.out.println("\t\t\tCreating Space in axis: " + axisID);
        this.axisID = axisID;
        this.value = initialValue;
        this.vector = initialVector;
        System.out.println("\t\t\t\tValue: " + this.value);
    }
}

class Dimension {
    private int dimensionId;
    public Location[] positions;

    public Dimension(int dimensionId, int upperBound) {
        this.dimensionId = dimensionId;
        positions = new Location[upperBound];
        for (int i = 0; i < upperBound; i++) {
            positions[i] = new Location(0, 0, dimensionId);
        }
        System.out.println("\t\tCreated Dimension: " + dimensionId + " with size " + upperBound);
    }
}


interface DimensionalPoint {

}


interface DimensionalPoint1 extends DimensionalPoint {
    public Location getX();

    public void setX(Location newX);

    public Location[] location = new Location[1];
}

interface DimensionalPoint2 extends DimensionalPoint1 {
    public Location getY();

    public void setY(Location newX);

    public Location[] location = new Location[2];
}

interface DimensionalPoint3 extends DimensionalPoint2 {
    public Location getZ();

    public void setZ(Location newZ);

    public Location[] location = new Location[3];
}

interface DimensionalPoint4 extends DimensionalPoint3 {
    public Location getT();

    public void setT(Location newT);

    public Location[] location = new Location[4];
}


abstract class BaseDimensionalPoint implements DimensionalPoint {
    public int dimensionCount = 0;
}


class OneDimensionalPoint extends BaseDimensionalPoint implements DimensionalPoint1 {
    private Location x = new Location(0, 0, 0);
    public int dimensionCount = 1;

    @Override
    public Location getX() {
        return x;
    }

    @Override
    public void setX(Location newX) {
        x = newX;
    }
}

class TwoDimensionalPoint extends OneDimensionalPoint implements DimensionalPoint2 {
    private Location y = new Location(0, 0, 1);
    public int dimensionCount = 2;

    @Override
    public Location getY() {
        // TODO Auto-generated method stub
        return y;
    }

    @Override
    public void setY(Location newY) {
        y = newY;

    }
}

class ThreeDimensionalPoint extends TwoDimensionalPoint implements DimensionalPoint3 {
    private Location z = new Location(0, 0, 2);
    public int dimensionCount = 3;

    @Override
    public Location getZ() {
        // TODO Auto-generated method stub
        return z;
    }

    @Override
    public void setZ(Location newZ) {
        z = newZ;

    }
}

class FourDimensionalPoint extends ThreeDimensionalPoint implements DimensionalPoint4 {
    private Location t = new Location(0, 0, 3);
    public int dimensionCount = 4;

    @Override
    public Location getT() {
        // TODO Auto-generated method stub
        return t;
    }

    @Override
    public void setT(Location newT) {
        t = newT;

    }

}

public class Universe extends TheVoid {
    private final int MAX_DIM_SIZE = 5;
    public Dimension[] dimension;
    public Deity God;
    protected int uniqueID = 0;

    public static void RandomizeUniverse(Universe aUniverse, int Density) {
        System.out.println("Filling the universe with random data!");
        Random rnd = new Random();
        int numOfLoopsWeNeedToProcess = aUniverse.dimension.length;
        int processed = 0;
        int aNum = 0;
        do {
            for (int i = 0; i < aUniverse.dimension[processed].positions.length; i++) {
                aNum = rnd.nextInt(100);
                if (aNum >= Density) {
                    aUniverse.dimension[processed].positions[i].value = rnd.nextInt(Integer.MAX_VALUE);
                } else {
                    aUniverse.dimension[processed].positions[i].value = 0;
                }
                System.out.println("\tDimension[" + processed + ", " + aUniverse.dimension[processed].positions[i].axisID + "] - Value: " + aUniverse.dimension[processed].positions[i].value);

            }
            processed++;
        } while (processed < numOfLoopsWeNeedToProcess);
    }


    public Universe(int uniqueID) {
        this(new FourDimensionalPoint(), 4);
        this.uniqueID = uniqueID;
        Universe.RandomizeUniverse(this, 50);
        System.out.println("A new Universe is Born! - Bask in our Creators Glory!");
        System.out.println("\t  UniqueID: " + this.uniqueID);
        System.out.println("\tDimensions: " + this.dimension.length);

    }

    protected Universe(BaseDimensionalPoint dimensionPoints, int dimensionCount) {
        System.out.println("B A N G!");
        System.out.println("\tCreating a new Universe with " + dimensionCount + " dimensions, hold on to your socks!");

        System.out.println("\tAllocating Space for our dimensions");
        this.dimension = new Dimension[dimensionCount];
        System.out.println("\tInitializing the Dimensions");
        for (int i = 0; i < dimensionCount; i++) {

            this.dimension[i] = new Dimension(i, MAX_DIM_SIZE);
            System.out.println("\t\t\tAxis: " + i);
            System.out.println("\t\t\tSize: " + this.dimension[i].positions.length);
        }

    }

}
