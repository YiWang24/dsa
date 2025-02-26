package com.wycode.datastructures.DisJointSets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author WY
 * @version 1.0
 **/

public class WeightedQuickUnionDSTest {
    private WeightedQuickUnionDS uf;
    @BeforeEach
    void setUp() {
        uf = new WeightedQuickUnionDS(6); 
    }

    @Test
    void testInitialization() {
        for (int i = 0; i < 6; i++) {
            assertEquals(1, uf.sizeOf(i));
            assertEquals(i, uf.find(i)); 
        }
    }



    @Test
    void testSizeOf() {
        uf.connect(0, 1);
        assertEquals(2, uf.sizeOf(0)); 
        assertEquals(2, uf.sizeOf(1));

        uf.connect(2, 3);
        assertEquals(2, uf.sizeOf(2));

        uf.connect(0, 3);
        assertEquals(4, uf.sizeOf(0));
    }

    @Test
    void testParentMethod() {
        uf.connect(0, 1);
        assertEquals(0, uf.find(1));
    }

    @Test
    void testConnected() {
        assertFalse(uf.isConnected(0, 1));
        uf.connect(0, 1);
        assertTrue(uf.isConnected(0, 1));
        assertFalse(uf.isConnected(1, 2));

        uf.connect(2, 3);
        assertTrue(uf.isConnected(2, 3));
        assertFalse(uf.isConnected(0, 3));

        uf.connect(1, 3);
        assertTrue(uf.isConnected(0, 3));
    }

    @Test
    void testUnionBySize() {
        uf.connect(0, 1);
        uf.connect(2, 3);
        uf.connect(4, 5);

        uf.connect(1, 3);
        assertEquals(4, uf.sizeOf(0));
        assertEquals(4, uf.sizeOf(3));

        uf.connect(3, 5);
        assertEquals(6, uf.sizeOf(0));
        assertTrue(uf.isConnected(0, 5));
    }

    @Test
    void testFindWithPathCompression() {
        uf.connect(0, 1);
        uf.connect(1, 2);
        uf.connect(2, 3);
        uf.connect(3, 4);

        int rootBefore = uf.find(4);
        assertEquals(0, rootBefore); 

      
        assertEquals(0, uf.parent(4));
        assertEquals(0, uf.parent(3));
        assertEquals(0, uf.parent(2));
        assertEquals(0, uf.parent(1));
    }
}
