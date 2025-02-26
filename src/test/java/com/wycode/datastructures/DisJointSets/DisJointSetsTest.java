package com.wycode.datastructures.DisJointSets;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author WY
 * @version 1.0
 **/

public class DisJointSetsTest {
    @Test
    public void testQuickFindDS() {
        QuickFindDS quickFind = new QuickFindDS(10);

        // Union operations
        quickFind.connect(1, 2);
        quickFind.connect(2, 3);
        quickFind.connect(4, 5);

        assertTrue(quickFind.isConnected(1, 3), "1 and 3 should be connected");
        assertFalse(quickFind.isConnected(1, 4), "1 and 4 should not be connected");
        assertTrue(quickFind.isConnected(4, 5), "4 and 5 should be connected");
    }

    @Test
    public void testQuickUnionDS() {
        QuickUnionDS quickUnion = new QuickUnionDS(10);



        // Union operations
        quickUnion.connect(1, 2);
        quickUnion.connect(2, 3);
        quickUnion.connect(4, 5);
        quickUnion.connect(3, 4); // Connecting the two groups

        assertTrue(quickUnion.isConnected(1, 5), "1 and 5 should be connected after unions");
        assertFalse(quickUnion.isConnected(1, 6), "1 and 6 should not be connected");
    }

    @Test
    public void testWeightedQuickUnionDS() {
        WeightedQuickUnionDS quickUnion = new WeightedQuickUnionDS(10);

        // Union operations
        quickUnion.connect(0, 1);
        quickUnion.connect(2, 3);
        quickUnion.connect(0, 2);
        quickUnion.connect(4, 5);
        quickUnion.connect(6, 7);
        quickUnion.connect(4, 6);
        quickUnion.connect(0, 4);

        quickUnion.isConnected(1,5);
        quickUnion.isConnected(0,3);
        assertTrue(quickUnion.isConnected(1, 5), "1 and 5 should be connected after unions");
        assertFalse(quickUnion.isConnected(1, 6), "1 and 6 should not be connected");
    }


}
