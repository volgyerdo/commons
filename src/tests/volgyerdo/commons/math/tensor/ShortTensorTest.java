/*
 * Copyright 2021 Volgyerdo Nonprofit Kft.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package volgyerdo.commons.math.tensor;

import org.junit.Test;
import static org.junit.Assert.*;
import volgyerdo.commons.primitive.PrimitiveUtils;

/**
 *
 * @author Volgyerdo Nonprofit Kft.
 */
public class ShortTensorTest {

    private static final double FLOATING_VALUE_ACCURACY = 0.0001;

    public ShortTensorTest() {
    }

    @Test
    public void testSetByteValue() {
        byte[][][] values = new byte[4][5][6];
        ShortTensor t = new ShortTensor(4, 5, 6);
        for (byte i = 0; i < 4; i++) {
            for (byte j = 0; j < 5; j++) {
                for (byte k = 0; k < 6; k++) {
                    values[i][j][k] = (byte) (Math.random() * (Byte.MAX_VALUE - Byte.MIN_VALUE) - Byte.MAX_VALUE - 1);
                    t.setByteValue(values[i][j][k], i, j, k);
                }
            }
        }
        for (byte i = 0; i < 4; i++) {
            for (byte j = 0; j < 5; j++) {
                for (byte k = 0; k < 6; k++) {
                    assertEquals("Byte value match (" + i + "," + j + "," + k + ")",
                            (short) values[i][j][k], t.values[i + 4 * j + 4 * 5 * k]);
                }
            }
        }
    }

    @Test
    public void testSetFloatValue() {
        float[][][] values = new float[8][4][3];
        ShortTensor t = new ShortTensor(8, 4, 3);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 3; k++) {
                    values[i][j][k] = (float) (Math.random()
                            * ((double) Float.MAX_VALUE * 2) - Float.MAX_VALUE);
                    t.setFloatValue(values[i][j][k], i, j, k);
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 3; k++) {
                    short shortValue = PrimitiveUtils.toShort(values[i][j][k]);
                    assertEquals("Float value match (" + i + "," + j + "," + k + ")",
                            shortValue, t.values[i + 8 * j + 8 * 4 * k]);
                }
            }
        }
    }

    @Test
    public void testSetShortValue() {
        short[][][] values = new short[8][4][3];
        ShortTensor t = new ShortTensor(8, 4, 3);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 3; k++) {
                    values[i][j][k] = (short) (Math.random() * (Short.MAX_VALUE - Short.MIN_VALUE) - Short.MAX_VALUE);
                    t.setShortValue(values[i][j][k], i, j, k);
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 3; k++) {
                    assertEquals("Short value match (" + i + "," + j + "," + k + ")",
                            values[i][j][k], t.values[i + 8 * j + 8 * 4 * k]);
                }
            }
        }
    }

    @Test
    public void testSetObjectValue() {
        try {
            ShortTensor t = new ShortTensor(1);
            t.setObjectValue(new Object(), 0);
            fail("Byte tensor accepts object");
        } catch (Exception e) {
            assertTrue("Put object into short tensor.", e instanceof RuntimeException);
        }
    }

    @Test
    public void testGetByteValue() {
        short[][][] values = new short[4][5][6];
        ShortTensor t = new ShortTensor(4, 5, 6);
        for (byte i = 0; i < 4; i++) {
            for (byte j = 0; j < 5; j++) {
                for (byte k = 0; k < 6; k++) {
                    values[i][j][k] = (short) (Math.random() * (Short.MAX_VALUE - Short.MIN_VALUE) - Short.MAX_VALUE - 1);
                    t.setShortValue(values[i][j][k], i, j, k);
                }
            }
        }
        for (byte i = 0; i < 4; i++) {
            for (byte j = 0; j < 5; j++) {
                for (byte k = 0; k < 6; k++) {
                    assertEquals("Byte value match (" + i + "," + j + "," + k + ")",
                            t.getByteValue(i, j, k), PrimitiveUtils.toByte(t.values[i + 4 * j + 4 * 5 * k]));
                }
            }
        }
    }

    @Test
    public void testGetShortValue() {
        short[][][] values = new short[4][5][6];
        ShortTensor t = new ShortTensor(4, 5, 6);
        for (byte i = 0; i < 4; i++) {
            for (byte j = 0; j < 5; j++) {
                for (byte k = 0; k < 6; k++) {
                    values[i][j][k] = (short) (Math.random() * (Short.MAX_VALUE - Short.MIN_VALUE) - Short.MAX_VALUE - 1);
                    t.setShortValue(values[i][j][k], i, j, k);
                }
            }
        }
        for (byte i = 0; i < 4; i++) {
            for (byte j = 0; j < 5; j++) {
                for (byte k = 0; k < 6; k++) {
                    assertEquals("Short value match (" + i + "," + j + "," + k + ")",
                            t.getShortValue(i, j, k), t.values[i + 4 * j + 4 * 5 * k]);
                }
            }
        }
    }

    @Test
    public void testGetFloatValue() {
        short[][][] values = new short[4][5][6];
        ShortTensor t = new ShortTensor(4, 5, 6);
        for (byte i = 0; i < 4; i++) {
            for (byte j = 0; j < 5; j++) {
                for (byte k = 0; k < 6; k++) {
                    values[i][j][k] = (short) (Math.random() * (Short.MAX_VALUE - Short.MIN_VALUE) - Short.MAX_VALUE - 1);
                    t.setShortValue(values[i][j][k], i, j, k);
                }
            }
        }
        for (byte i = 0; i < 4; i++) {
            for (byte j = 0; j < 5; j++) {
                for (byte k = 0; k < 6; k++) {
                    assertEquals("Float value match (" + i + "," + j + "," + k + ")",
                            t.getFloatValue(i, j, k), PrimitiveUtils.toFloat(t.values[i + 4 * j + 4 * 5 * k]), FLOATING_VALUE_ACCURACY);
                }
            }
        }
    }

    @Test
    public void testGetObjectValue() {
        try {
            ShortTensor t = new ShortTensor(1);
            t.setObjectValue(new Object(), 0);
            fail("Short tensor returns object");
        } catch (Exception e) {
            assertTrue("Get object from short tensor.", e instanceof RuntimeException);
        }
    }

    @Test
    public void testEquals() {
        ShortTensor t1 = new ShortTensor(1);
        t1.setShortValue((short) 0, 0);
        ShortTensor t2 = new ShortTensor(1);
        t2.setShortValue((short) 0, 0);
        assertTrue("One element tensors equal", t1.equals(t2));
        t2.setShortValue((short) 5, 0);
        assertFalse("One element tensors differ", t1.equals(t2));
        ShortTensor t3 = new ShortTensor(3, 2);
        assertFalse("Different dimension tenzors differ", t1.equals(t3));
        FloatTensor t4 = new FloatTensor(3, 2);
        assertFalse("Different type tenzors differ", t1.equals(t4));
        ShortTensor t5 = new ShortTensor(3, 2);
        t3.setShortValue((short) -1, 0, 0);
        t3.setShortValue((short) 0, 1, 0);
        t3.setShortValue((short) 10, 2, 0);
        t3.setShortValue((short) 15, 0, 1);
        t3.setShortValue((short) 20, 1, 1);
        t3.setShortValue((short) 99, 2, 1);
        assertFalse("Multidimensional tensors differ", t3.equals(t5));
        t5.setShortValue((short) -1, 0, 0);
        t5.setShortValue((short) 0, 1, 0);
        t5.setShortValue((short) 10, 2, 0);
        t5.setShortValue((short) 15, 0, 1);
        t5.setShortValue((short) 20, 1, 1);
        t5.setShortValue((short) 99, 2, 1);
        assertTrue("Multidimensional tensors equals", t3.equals(t5));
    }

    @Test
    public void testRandomizeByte() {
        ShortTensor t = new ShortTensor(3, 5, 4);
        try {
            t.randomize((byte) 43, (byte) 3);
            fail("Bad random bounds.");
        } catch (Exception e) {
            assertTrue("Bad random bounds.", e instanceof RuntimeException);
        }
        t.randomize((byte) -55, (byte) 43);
        short min = Short.MAX_VALUE, max = Short.MIN_VALUE;
        for (byte i = 0; i < 3 * 2 * 4; i++) {
            if (t.values[i] < min) {
                min = t.values[i];
            }
            if (t.values[i] > max) {
                max = t.values[i];
            }
        }
        assertTrue("Randomized byte value in bounds (" + min + "," + max + ")",
                min >= -55 && max <= 43 && min < max);
    }

    @Test
    public void testRandomizeShort() {
        ShortTensor t = new ShortTensor(3, 5, 4);
        try {
            t.randomize((short) -45, (short) -98);
            fail("Bad random bounds.");
        } catch (Exception e) {
            assertTrue("Bad random bounds.", e instanceof RuntimeException);
        }
        t.randomize((short) -550, (short) 4300);
        short min = Short.MAX_VALUE, max = Short.MIN_VALUE;
        for (byte i = 0; i < 3 * 2 * 4; i++) {
            if (t.values[i] < min) {
                min = t.values[i];
            }
            if (t.values[i] > max) {
                max = t.values[i];
            }
        }
        assertTrue("Randomized short value in bounds (" + min + "," + max + ")",
                min >= Short.MIN_VALUE && max <= Short.MAX_VALUE && min < max);
    }

    @Test
    public void testRandomizeFloat() {
        ShortTensor t = new ShortTensor(3, 5, 4);
        try {
            t.randomize((float) 33, (float) 32);
            fail("Bad random bounds.");
        } catch (Exception e) {
            assertTrue("Bad random bounds.", e instanceof RuntimeException);
        }
        t.randomize((float) -7693.2234, (float) 9933.232);
        short min = Short.MAX_VALUE, max = Short.MIN_VALUE;
        for (byte i = 0; i < 3 * 2 * 4; i++) {
            if (t.values[i] < min) {
                min = t.values[i];
            }
            if (t.values[i] > max) {
                max = t.values[i];
            }
        }
        assertTrue("Randomized short value in bounds (" + min + "," + max + ")",
                min >= Short.MIN_VALUE && max <= Short.MAX_VALUE && min < max);
    }

    @Test
    public void testAddByte() {
        ShortTensor t = new ShortTensor(3, 2);
        t.setShortValue((short) -1, 0, 0);
        t.setShortValue((short) 0, 1, 0);
        t.setShortValue((short) 10, 2, 0);
        t.setShortValue((short) 15, 0, 1);
        t.setShortValue((short) 20, 1, 1);
        t.setShortValue((short) 99, 2, 1);
        t.add((short) 0);
        assertEquals("Add 0 value", (byte) -1, t.getShortValue(0, 0));
        assertEquals("Add 0 value", (byte) 0, t.getShortValue(1, 0));
        assertEquals("Add 0 value", (byte) 10, t.getShortValue(2, 0));
        assertEquals("Add 0 value", (byte) 15, t.getShortValue(0, 1));
        assertEquals("Add 0 value", (byte) 20, t.getShortValue(1, 1));
        assertEquals("Add 0 value", (byte) 99, t.getShortValue(2, 1));
        t.add((short) 10);
        assertEquals("Add 10 value", (byte) 9, t.getShortValue(0, 0));
        assertEquals("Add 10 value", (byte) 10, t.getShortValue(1, 0));
        assertEquals("Add 10 value", (byte) 20, t.getShortValue(2, 0));
        assertEquals("Add 10 value", (byte) 25, t.getShortValue(0, 1));
        assertEquals("Add 10 value", (byte) 30, t.getShortValue(1, 1));
        assertEquals("Add 10 value", (byte) 109, t.getShortValue(2, 1));
        t.add((short) -5);
        assertEquals("Add 10 value", (byte) 4, t.getShortValue(0, 0));
        assertEquals("Add 10 value", (byte) 5, t.getShortValue(1, 0));
        assertEquals("Add 10 value", (byte) 15, t.getShortValue(2, 0));
        assertEquals("Add 10 value", (byte) 20, t.getShortValue(0, 1));
        assertEquals("Add 10 value", (byte) 25, t.getShortValue(1, 1));
        assertEquals("Add 10 value", (byte) 104, t.getShortValue(2, 1));
    }

    @Test
    public void testAddShort() {
        ShortTensor t = new ShortTensor(3, 2);
        t.setShortValue((short) -1, 0, 0);
        t.setShortValue((short) 0, 1, 0);
        t.setShortValue((short) 10, 2, 0);
        t.setShortValue((short) 15, 0, 1);
        t.setShortValue((short) 20, 1, 1);
        t.setShortValue((short) 99, 2, 1);
        t.add((short) 0);
        assertEquals("Add 0 value", (byte) -1, t.getShortValue(0, 0));
        assertEquals("Add 0 value", (byte) 0, t.getShortValue(1, 0));
        assertEquals("Add 0 value", (byte) 10, t.getShortValue(2, 0));
        assertEquals("Add 0 value", (byte) 15, t.getShortValue(0, 1));
        assertEquals("Add 0 value", (byte) 20, t.getShortValue(1, 1));
        assertEquals("Add 0 value", (byte) 99, t.getShortValue(2, 1));
        t.add((short) 10);
        assertEquals("Add 10 value", (byte) 9, t.getShortValue(0, 0));
        assertEquals("Add 10 value", (byte) 10, t.getShortValue(1, 0));
        assertEquals("Add 10 value", (byte) 20, t.getShortValue(2, 0));
        assertEquals("Add 10 value", (byte) 25, t.getShortValue(0, 1));
        assertEquals("Add 10 value", (byte) 30, t.getShortValue(1, 1));
        assertEquals("Add 10 value", (byte) 109, t.getShortValue(2, 1));
        t.add((short) -5);
        assertEquals("Add 10 value", (byte) 4, t.getShortValue(0, 0));
        assertEquals("Add 10 value", (byte) 5, t.getShortValue(1, 0));
        assertEquals("Add 10 value", (byte) 15, t.getShortValue(2, 0));
        assertEquals("Add 10 value", (byte) 20, t.getShortValue(0, 1));
        assertEquals("Add 10 value", (byte) 25, t.getShortValue(1, 1));
        assertEquals("Add 10 value", (byte) 104, t.getShortValue(2, 1));
    }

    @Test
    public void testAddFloat() {
        ShortTensor t = new ShortTensor(3, 2);
        t.setShortValue((short) -1, 0, 0);
        t.setShortValue((short) 0, 1, 0);
        t.setShortValue((short) 10, 2, 0);
        t.setShortValue((short) 15, 0, 1);
        t.setShortValue((short) 20, 1, 1);
        t.setShortValue((short) 99, 2, 1);
        t.add((float) 0);
        assertEquals("Add 0 value", (byte) -1, t.getShortValue(0, 0));
        assertEquals("Add 0 value", (byte) 0, t.getShortValue(1, 0));
        assertEquals("Add 0 value", (byte) 10, t.getShortValue(2, 0));
        assertEquals("Add 0 value", (byte) 15, t.getShortValue(0, 1));
        assertEquals("Add 0 value", (byte) 20, t.getShortValue(1, 1));
        assertEquals("Add 0 value", (byte) 99, t.getShortValue(2, 1));
        t.add((float) 10);
        assertEquals("Add 10 value", (byte) 9, t.getShortValue(0, 0));
        assertEquals("Add 10 value", (byte) 10, t.getShortValue(1, 0));
        assertEquals("Add 10 value", (byte) 20, t.getShortValue(2, 0));
        assertEquals("Add 10 value", (byte) 25, t.getShortValue(0, 1));
        assertEquals("Add 10 value", (byte) 30, t.getShortValue(1, 1));
        assertEquals("Add 10 value", (byte) 109, t.getShortValue(2, 1));
        t.add((float) -5);
        assertEquals("Add 10 value", (byte) 4, t.getShortValue(0, 0));
        assertEquals("Add 10 value", (byte) 5, t.getShortValue(1, 0));
        assertEquals("Add 10 value", (byte) 15, t.getShortValue(2, 0));
        assertEquals("Add 10 value", (byte) 20, t.getShortValue(0, 1));
        assertEquals("Add 10 value", (byte) 25, t.getShortValue(1, 1));
        assertEquals("Add 10 value", (byte) 104, t.getShortValue(2, 1));
    }

    @Test
    public void testAddTensor() {
        ShortTensor t1 = new ShortTensor(3, 2);
        try {
            t1.add(null);
            fail("Add null tensor");
        } catch (Exception e) {
            assertTrue("Add null tensor.", e instanceof RuntimeException);
        }
        ByteTensor t2 = new ByteTensor(3, 2);
        try {
            t1.add(t2);
            fail("Add different type of tensor");
        } catch (Exception e) {
            assertTrue("Add different type of tensor.", e instanceof RuntimeException);
        }
        ShortTensor t3 = new ShortTensor(3, 2, 5);
        try {
            t1.add(t3);
            fail("Add different size of tensor");
        } catch (Exception e) {
            assertTrue("Add different size of tensor.", e instanceof RuntimeException);
        }
        t1.setShortValue((short) -34, 0, 0);
        t1.setShortValue((short) -67, 1, 0);
        t1.setShortValue((short) 6, 2, 0);
        t1.setShortValue((short) 34, 0, 1);
        t1.setShortValue((short) 2, 1, 1);
        t1.setShortValue((short) 286324, 2, 1);
        ShortTensor t4 = new ShortTensor(3, 2);
        t4.setShortValue((short) 2, 0, 0);
        t4.setShortValue((short) -3, 1, 0);
        t4.setShortValue((short) 10, 2, 0);
        t4.setShortValue((short) -10, 0, 1);
        t4.setShortValue((short) 7, 1, 1);
        t4.setShortValue((short) 54356, 2, 1);
        t1.add(t4);
        assertEquals("Tensor sum", (short) -32, t1.getShortValue(0, 0));
        assertEquals("Tensor sum", (short) -70, t1.getShortValue(1, 0));
        assertEquals("Tensor sum", (short) 16, t1.getShortValue(2, 0));
        assertEquals("Tensor sum", (short) 24, t1.getShortValue(0, 1));
        assertEquals("Tensor sum", (short) 9, t1.getShortValue(1, 1));
        assertEquals("Tensor sum", (short) 340680, t1.getShortValue(2, 1));
    }

    @Test
    public void testSubstractByte() {
        ShortTensor t = new ShortTensor(3, 2);
        t.setShortValue((short) -1, 0, 0);
        t.setShortValue((short) 0, 1, 0);
        t.setShortValue((short) 10, 2, 0);
        t.setShortValue((short) 15, 0, 1);
        t.setShortValue((short) 20, 1, 1);
        t.setShortValue((short) 99, 2, 1);
        t.substract((short) 0);
        assertEquals("Add 0 value", (short) -1, t.getShortValue(0, 0));
        assertEquals("Add 0 value", (short) 0, t.getShortValue(1, 0));
        assertEquals("Add 0 value", (short) 10, t.getShortValue(2, 0));
        assertEquals("Add 0 value", (short) 15, t.getShortValue(0, 1));
        assertEquals("Add 0 value", (short) 20, t.getShortValue(1, 1));
        assertEquals("Add 0 value", (short) 99, t.getShortValue(2, 1));
        t.substract((short) 2);
        assertEquals("Add 10 value", (short) -3, t.getShortValue(0, 0));
        assertEquals("Add 10 value", (short) -2, t.getShortValue(1, 0));
        assertEquals("Add 10 value", (short) 8, t.getShortValue(2, 0));
        assertEquals("Add 10 value", (short) 13, t.getShortValue(0, 1));
        assertEquals("Add 10 value", (short) 18, t.getShortValue(1, 1));
        assertEquals("Add 10 value", (short) 97, t.getShortValue(2, 1));
        t.substract((short) -5);
        assertEquals("Add 10 value", (short) 2, t.getShortValue(0, 0));
        assertEquals("Add 10 value", (short) 3, t.getShortValue(1, 0));
        assertEquals("Add 10 value", (short) 13, t.getShortValue(2, 0));
        assertEquals("Add 10 value", (short) 18, t.getShortValue(0, 1));
        assertEquals("Add 10 value", (short) 23, t.getShortValue(1, 1));
        assertEquals("Add 10 value", (short) 102, t.getShortValue(2, 1));
    }

    @Test
    public void testSubstarctShort() {
        ShortTensor t = new ShortTensor(3, 2);
        t.setShortValue((short) -1, 0, 0);
        t.setShortValue((short) 0, 1, 0);
        t.setShortValue((short) 10, 2, 0);
        t.setShortValue((short) 15, 0, 1);
        t.setShortValue((short) 20, 1, 1);
        t.setShortValue((short) 99, 2, 1);
        t.substract((short) 0);
        assertEquals("Add 0 value", (short) -1, t.getShortValue(0, 0));
        assertEquals("Add 0 value", (short) 0, t.getShortValue(1, 0));
        assertEquals("Add 0 value", (short) 10, t.getShortValue(2, 0));
        assertEquals("Add 0 value", (short) 15, t.getShortValue(0, 1));
        assertEquals("Add 0 value", (short) 20, t.getShortValue(1, 1));
        assertEquals("Add 0 value", (short) 99, t.getShortValue(2, 1));
        t.substract((short) 2);
        assertEquals("Add 10 value", (short) -3, t.getShortValue(0, 0));
        assertEquals("Add 10 value", (short) -2, t.getShortValue(1, 0));
        assertEquals("Add 10 value", (short) 8, t.getShortValue(2, 0));
        assertEquals("Add 10 value", (short) 13, t.getShortValue(0, 1));
        assertEquals("Add 10 value", (short) 18, t.getShortValue(1, 1));
        assertEquals("Add 10 value", (short) 97, t.getShortValue(2, 1));
        t.substract((short) -5);
        assertEquals("Add 10 value", (short) 2, t.getShortValue(0, 0));
        assertEquals("Add 10 value", (short) 3, t.getShortValue(1, 0));
        assertEquals("Add 10 value", (short) 13, t.getShortValue(2, 0));
        assertEquals("Add 10 value", (short) 18, t.getShortValue(0, 1));
        assertEquals("Add 10 value", (short) 23, t.getShortValue(1, 1));
        assertEquals("Add 10 value", (short) 102, t.getShortValue(2, 1));
    }

    @Test
    public void testSubstractFloat() {
        ShortTensor t = new ShortTensor(3, 2);
        t.setShortValue((short) -1, 0, 0);
        t.setShortValue((short) 0, 1, 0);
        t.setShortValue((short) 10, 2, 0);
        t.setShortValue((short) 15, 0, 1);
        t.setShortValue((short) 20, 1, 1);
        t.setShortValue((short) 99, 2, 1);
        t.substract((float) 0);
        assertEquals("Add 0 value", (short) -1, t.getShortValue(0, 0));
        assertEquals("Add 0 value", (short) 0, t.getShortValue(1, 0));
        assertEquals("Add 0 value", (short) 10, t.getShortValue(2, 0));
        assertEquals("Add 0 value", (short) 15, t.getShortValue(0, 1));
        assertEquals("Add 0 value", (short) 20, t.getShortValue(1, 1));
        assertEquals("Add 0 value", (short) 99, t.getShortValue(2, 1));
        t.substract((float) 2);
        assertEquals("Add 10 value", (short) -3, t.getShortValue(0, 0));
        assertEquals("Add 10 value", (short) -2, t.getShortValue(1, 0));
        assertEquals("Add 10 value", (short) 8, t.getShortValue(2, 0));
        assertEquals("Add 10 value", (short) 13, t.getShortValue(0, 1));
        assertEquals("Add 10 value", (short) 18, t.getShortValue(1, 1));
        assertEquals("Add 10 value", (short) 97, t.getShortValue(2, 1));
        t.substract((float) -5);
        assertEquals("Add 10 value", (short) 2, t.getShortValue(0, 0));
        assertEquals("Add 10 value", (short) 3, t.getShortValue(1, 0));
        assertEquals("Add 10 value", (short) 13, t.getShortValue(2, 0));
        assertEquals("Add 10 value", (short) 18, t.getShortValue(0, 1));
        assertEquals("Add 10 value", (short) 23, t.getShortValue(1, 1));
        assertEquals("Add 10 value", (short) 102, t.getShortValue(2, 1));
    }

    @Test
    public void testSubstarctTensor() {
        ShortTensor t1 = new ShortTensor(3, 2);
        try {
            t1.substract(null);
            fail("Add null tensor");
        } catch (Exception e) {
            assertTrue("Add null tensor.", e instanceof RuntimeException);
        }
        ByteTensor t2 = new ByteTensor(3, 2);
        try {
            t1.substract(t2);
            fail("Add different type of tensor");
        } catch (Exception e) {
            assertTrue("Add different type of tensor.", e instanceof RuntimeException);
        }
        ShortTensor t3 = new ShortTensor(3, 2, 5);
        try {
            t1.substract(t3);
            fail("Add different size of tensor");
        } catch (Exception e) {
            assertTrue("Add different size of tensor.", e instanceof RuntimeException);
        }
        t1.setShortValue((short) -34, 0, 0);
        t1.setShortValue((short) -67, 1, 0);
        t1.setShortValue((short) 6, 2, 0);
        t1.setShortValue((short) 34, 0, 1);
        t1.setShortValue((short) 2, 1, 1);
        t1.setShortValue((short) 286324, 2, 1);
        ShortTensor t4 = new ShortTensor(3, 2);
        t4.setShortValue((short) 2, 0, 0);
        t4.setShortValue((short) -3, 1, 0);
        t4.setShortValue((short) 10, 2, 0);
        t4.setShortValue((short) -10, 0, 1);
        t4.setShortValue((short) 7, 1, 1);
        t4.setShortValue((short) 54356, 2, 1);
        t1.substract(t4);
        assertEquals("Tensor sum", (short) -36, t1.getShortValue(0, 0));
        assertEquals("Tensor sum", (short) -64, t1.getShortValue(1, 0));
        assertEquals("Tensor sum", (short) -4, t1.getShortValue(2, 0));
        assertEquals("Tensor sum", (short) 44, t1.getShortValue(0, 1));
        assertEquals("Tensor sum", (short) -5, t1.getShortValue(1, 1));
        assertEquals("Tensor sum", (short) 231968, t1.getShortValue(2, 1));
    }

    @Test
    public void testNegateTensor() {
        ShortTensor t1 = new ShortTensor(3, 2);
        t1.setShortValue((short) 2, 0, 0);
        t1.setShortValue((short) -3, 1, 0);
        t1.setShortValue((short) 10, 2, 0);
        t1.setShortValue((short) -10, 0, 1);
        t1.setShortValue((short) 7, 1, 1);
        t1.setShortValue((short) -22, 2, 1);
        t1.negate();
        assertEquals("Tensor negate", (byte) -2, t1.getShortValue(0, 0));
        assertEquals("Tensor negate", (byte) 3, t1.getShortValue(1, 0));
        assertEquals("Tensor negate", (byte) -10, t1.getShortValue(2, 0));
        assertEquals("Tensor negate", (byte) 10, t1.getShortValue(0, 1));
        assertEquals("Tensor negate", (byte) -7, t1.getShortValue(1, 1));
        assertEquals("Tensor negate", (byte) 22, t1.getShortValue(2, 1));
    }

    @Test
    public void testTransposeTensor() {
        ShortTensor t1 = new ShortTensor(3, 2);
        t1.setShortValue((short) -34, 0, 0);
        t1.setShortValue((short) -67, 1, 0);
        t1.setShortValue((short) 6, 2, 0);
        t1.setShortValue((short) 34, 0, 1);
        t1.setShortValue((short) 2, 1, 1);
        t1.setShortValue((short) 78, 2, 1);
        Tensor transposed = t1.transpose();
        assertEquals("Transposed tensor", (byte) -34, transposed.getShortValue(0, 0));
        assertEquals("Transposed tensor", (byte) -67, transposed.getShortValue(0, 1));
        assertEquals("Transposed tensor", (byte) 6, transposed.getShortValue(0, 2));
        assertEquals("Transposed tensor", (byte) 34, transposed.getShortValue(1, 0));
        assertEquals("Transposed tensor", (byte) 2, transposed.getShortValue(1, 1));
        assertEquals("Transposed tensor", (byte) 78, transposed.getShortValue(1, 2));
    }
    
    @Test
    public void testRotateTensor() {
        ShortTensor t1 = new ShortTensor(3, 2);
        t1.setShortValue((short) -34, 0, 0);
        t1.setShortValue((short) -67, 1, 0);
        t1.setShortValue((short) 6, 2, 0);
        t1.setShortValue((short) 34, 0, 1);
        t1.setShortValue((short) 2, 1, 1);
        t1.setShortValue((short) 78, 2, 1);
        Tensor rotated = t1.rotate();
        assertEquals("Transposed tensor", (byte) -34, rotated.getShortValue(2, 1));
        assertEquals("Transposed tensor", (byte) -67, rotated.getShortValue(1, 1));
        assertEquals("Transposed tensor", (byte) 6, rotated.getShortValue(0, 1));
        assertEquals("Transposed tensor", (byte) 34, rotated.getShortValue(2, 0));
        assertEquals("Transposed tensor", (byte) 2, rotated.getShortValue(1, 0));
        assertEquals("Transposed tensor", (byte) 78, rotated.getShortValue(0, 0));
    }

    @Test
    public void testCloneTensor() throws CloneNotSupportedException {
        ShortTensor t1 = new ShortTensor(3, 2);
        t1.setShortValue((short) -34, 0, 0);
        t1.setShortValue((short) -67, 1, 0);
        t1.setShortValue((short) 6, 2, 0);
        t1.setShortValue((short) 34, 0, 1);
        t1.setShortValue((short) 2, 1, 1);
        t1.setShortValue((short) 78, 2, 1);
        Tensor transposed = t1.copy();
        assertEquals("Transposed tensor", (byte) -34, transposed.getShortValue(0, 0));
        assertEquals("Transposed tensor", (byte) -67, transposed.getShortValue(1, 0));
        assertEquals("Transposed tensor", (byte) 6, transposed.getShortValue(2, 0));
        assertEquals("Transposed tensor", (byte) 34, transposed.getShortValue(0, 1));
        assertEquals("Transposed tensor", (byte) 2, transposed.getShortValue(1, 1));
        assertEquals("Transposed tensor", (byte) 78, transposed.getShortValue(2, 1));
    }

    @Test
    public void testHadamardProduct() {
        ShortTensor t1 = new ShortTensor(3, 2);
        try {
            t1.substract(null);
            fail("Multiply null tensor");
        } catch (Exception e) {
            assertTrue("Multiply null tensor.", e instanceof RuntimeException);
        }
        ByteTensor t2 = new ByteTensor(3, 2);
        try {
            t1.substract(t2);
            fail("Multiply different type of tensor");
        } catch (Exception e) {
            assertTrue("Multiply different type of tensor.", e instanceof RuntimeException);
        }
        ShortTensor t3 = new ShortTensor(3, 2, 5);
        try {
            t1.substract(t3);
            fail("Multiply different size of tensor");
        } catch (Exception e) {
            assertTrue("Multiply different size of tensor.", e instanceof RuntimeException);
        }
        t1.setShortValue((short) -34, 0, 0);
        t1.setShortValue((short) -6, 1, 0);
        t1.setShortValue((short) 6, 2, 0);
        t1.setShortValue((short) 3, 0, 1);
        t1.setShortValue((short) 2, 1, 1);
        t1.setShortValue((short) 345, 2, 1);
        ShortTensor t4 = new ShortTensor(3, 2);
        t4.setShortValue((short) 2, 0, 0);
        t4.setShortValue((short) -3, 1, 0);
        t4.setShortValue((short) 10, 2, 0);
        t4.setShortValue((short) -10, 0, 1);
        t4.setShortValue((short) 7, 1, 1);
        t4.setShortValue((short) -22, 2, 1);
        t1.hadamardProduct(t4);
        assertEquals("Tensor sum", (short) -68, t1.getShortValue(0, 0));
        assertEquals("Tensor sum", (short) 18, t1.getShortValue(1, 0));
        assertEquals("Tensor sum", (short) 60, t1.getShortValue(2, 0));
        assertEquals("Tensor sum", (short) -30, t1.getShortValue(0, 1));
        assertEquals("Tensor sum", (short) 14, t1.getShortValue(1, 1));
        assertEquals("Tensor sum", (short) -7590, t1.getShortValue(2, 1));
    }

    @Test
    public void testMultiply() {
        Tensor a, b, c;

        a = new ShortTensor(1);
        a.setShortValue((short) 5, 0);
        System.out.println(a.toString());
        System.out.println("X\n");
        b = new ShortTensor(1);
        b.setShortValue((short) -3, 0);
        System.out.println(b.toString());
        System.out.println("=\n");
        c = a.multiply(b, 1);
        System.out.println(c.toString());
        System.out.println("---------------");
        assertEquals("1D-1D multiplication", (short) -15, c.getShortValue(0));

        a = new ShortTensor(2);
        a.setShortValue((short) 2, 0);
        a.setShortValue((short) 3, 1);
        System.out.println(a.toString());
        System.out.println("X\n");
        b = new ShortTensor(2);
        b.setShortValue((short) -5, 0);
        b.setShortValue((short) 4, 1);
        System.out.println(b.toString());
        System.out.println("=\n");
        c = a.multiply(b, 1);
        System.out.println(c.toString());
        System.out.println("---------------");
        assertEquals("1D-1D multiplication", (short) 2, c.getShortValue(0));

        a = new ShortTensor(2);
        a.setShortValue((short) 2, 0);
        a.setShortValue((short) 3, 1);
        System.out.println(a.toString());
        System.out.println("X\n");
        b = new ShortTensor(2);
        b.setShortValue((short) -5, 0);
        b.setShortValue((short) 4, 1);
        System.out.println(b.toString());
        System.out.println("=\n");
        c = a.multiply(b, 0);
        System.out.println(c.toString());
        System.out.println("---------------");
        assertEquals("1D-1D multiplication", (short) -10, c.getShortValue(0, 0));
        assertEquals("1D-1D multiplication", (short) 8, c.getShortValue(0, 1));
        assertEquals("1D-1D multiplication", (short) -15, c.getShortValue(1, 0));
        assertEquals("1D-1D multiplication", (short) 12, c.getShortValue(1, 1));

        a = new ShortTensor(2, 3);
        a.setShortValue((short) 2, 0, 0);
        a.setShortValue((short) -3, 0, 1);
        a.setShortValue((short) 9, 0, 2);
        a.setShortValue((short) -1, 1, 0);
        a.setShortValue((short) 0, 1, 1);
        a.setShortValue((short) 6, 1, 2);
        System.out.println(a.toString());
        System.out.println("X\n");
        b = new ShortTensor(3, 4);
        b.setShortValue((short) 2, 0, 0);
        b.setShortValue((short) -3, 0, 1);
        b.setShortValue((short) 9, 0, 2);
        b.setShortValue((short) 5, 0, 3);
        b.setShortValue((short) -1, 1, 0);
        b.setShortValue((short) 0, 1, 1);
        b.setShortValue((short) 6, 1, 2);
        b.setShortValue((short) -2, 1, 3);
        b.setShortValue((short) 7, 2, 0);
        b.setShortValue((short) 2, 2, 1);
        b.setShortValue((short) 1, 2, 2);
        b.setShortValue((short) 8, 2, 3);
        System.out.println(b.toString());
        System.out.println("=\n");
        c = a.multiply(b, 1);
        System.out.println(c.toString());
        System.out.println("---------------");
        assertEquals("2D-2D multiplication (0,0)", (short) 70, c.getShortValue(0, 0));
        assertEquals("2D-2D multiplication (1,0)", (short) 40, c.getShortValue(1, 0));
        assertEquals("2D-2D multiplication (0,1)", (short) 12, c.getShortValue(0, 1));
        assertEquals("2D-2D multiplication (1,1)", (short) 15, c.getShortValue(1, 1));
        assertEquals("2D-2D multiplication (0,2)", (short) 9, c.getShortValue(0, 2));
        assertEquals("2D-2D multiplication (1,2)", (short) -3, c.getShortValue(1, 2));
        assertEquals("2D-2D multiplication (0,3)", (short) 88, c.getShortValue(0, 3));
        assertEquals("2D-2D multiplication (1,3)", (short) 43, c.getShortValue(1, 3));

        a = new ShortTensor(2, 1);
        a.setShortValue((short) 2, 0, 0);
        a.setShortValue((short) -1, 1, 0);
        System.out.println(a.toString());
        System.out.println("X\n");
        b = new ShortTensor(2, 1, 3, 4);
        b.setShortValue((short) 4, 0, 0, 0, 0);
        b.setShortValue((short) 2, 0, 0, 0, 1);
        b.setShortValue((short) 1, 0, 0, 0, 2);
        b.setShortValue((short) 3, 0, 0, 0, 3);
        b.setShortValue((short) 5, 0, 0, 1, 0);
        b.setShortValue((short) 2, 0, 0, 1, 1);
        b.setShortValue((short) 1, 0, 0, 1, 2);
        b.setShortValue((short) 2, 0, 0, 1, 3);
        b.setShortValue((short) 1, 0, 0, 2, 0);
        b.setShortValue((short) 5, 0, 0, 2, 1);
        b.setShortValue((short) 4, 0, 0, 2, 2);
        b.setShortValue((short) 1, 0, 0, 2, 3);
        b.setShortValue((short) 3, 1, 0, 0, 0);
        b.setShortValue((short) 0, 1, 0, 0, 1);
        b.setShortValue((short) 5, 1, 0, 0, 2);
        b.setShortValue((short) 2, 1, 0, 0, 3);
        b.setShortValue((short) 1, 1, 0, 1, 0);
        b.setShortValue((short) 4, 1, 0, 1, 1);
        b.setShortValue((short) 0, 1, 0, 1, 2);
        b.setShortValue((short) 3, 1, 0, 1, 3);
        b.setShortValue((short) 2, 1, 0, 2, 0);
        b.setShortValue((short) 5, 1, 0, 2, 1);
        b.setShortValue((short) 0, 1, 0, 2, 2);
        b.setShortValue((short) 1, 1, 0, 2, 3);
        System.out.println(b.toString());
        System.out.println("=\n");
        c = a.multiply(b, 2);
        System.out.println(c.toString());
        System.out.println("---------------");
        assertEquals("2D-4D multiplication (0,0)", (short) 5, c.getShortValue(0, 0));
        assertEquals("2D-4D multiplication (1,0)", (short) 9, c.getShortValue(1, 0));
        assertEquals("2D-4D multiplication (2,0)", (short) 0, c.getShortValue(2, 0));
        assertEquals("2D-4D multiplication (0,1)", (short) 4, c.getShortValue(0, 1));
        assertEquals("2D-4D multiplication (1,1)", (short) 0, c.getShortValue(1, 1));
        assertEquals("2D-4D multiplication (2,1)", (short) 5, c.getShortValue(2, 1));
        assertEquals("2D-4D multiplication (0,2)", (short) -3, c.getShortValue(0, 2));
        assertEquals("2D-4D multiplication (1,2)", (short) 2, c.getShortValue(1, 2));
        assertEquals("2D-4D multiplication (2,2)", (short) 8, c.getShortValue(2, 2));
        assertEquals("2D-4D multiplication (0,3)", (short) 4, c.getShortValue(0, 3));
        assertEquals("2D-4D multiplication (1,3)", (short) 1, c.getShortValue(1, 3));
        assertEquals("2D-4D multiplication (2,3)", (short) 1, c.getShortValue(2, 3));
    }

    @Test
    public void testConvolve() {
        Tensor a, kernel, c;

        a = new ShortTensor(1);
        a.setShortValue((byte) 5, 0);
        System.out.println(a.toString());
        System.out.println("X\n");
        kernel = new ShortTensor(1);
        kernel.setShortValue((byte) -3, 0);
        System.out.println(kernel.toString());
        System.out.println("=\n");
        c = a.convolve(kernel);
        System.out.println(c.toString());
        System.out.println("---------------");
        assertEquals("0D convolution", (byte) -15, c.getShortValue(0));

        a = new ShortTensor(5);
        a.setShortValue((byte) 4, 0);
        a.setShortValue((byte) 2, 1);
        a.setShortValue((byte) -1, 2);
        a.setShortValue((byte) 6, 3);
        a.setShortValue((byte) -9, 4);
        System.out.println(a.toString());
        System.out.println("X\n");
        kernel = new ShortTensor(1);
        kernel.setShortValue((byte) 2, 0);
        System.out.println(kernel.toString());
        System.out.println("=\n");
        c = a.convolve(kernel);
        System.out.println(c.toString());
        System.out.println("---------------");
        assertEquals("1D convolution - kernel: [1] (1)", (byte) 8, c.getShortValue(0));
        assertEquals("1D convolution - kernel: [1] (2)", (byte) 4, c.getShortValue(1));
        assertEquals("1D convolution - kernel: [1] (3)", (byte) -2, c.getShortValue(2));
        assertEquals("1D convolution - kernel: [1] (4)", (byte) 12, c.getShortValue(3));
        assertEquals("1D convolution - kernel: [1] (5)", (byte) -18, c.getShortValue(4));

        a = new ShortTensor(5);
        a.setShortValue((byte) 4, 0);
        a.setShortValue((byte) 2, 1);
        a.setShortValue((byte) -1, 2);
        a.setShortValue((byte) 6, 3);
        a.setShortValue((byte) -9, 4);
        System.out.println(a.toString());
        System.out.println("X\n");
        kernel = new ShortTensor(3);
        kernel.setShortValue((byte) 0, 0);
        kernel.setShortValue((byte) -3, 1);
        kernel.setShortValue((byte) 0, 2);
        System.out.println(kernel.toString());
        System.out.println("=\n");
        c = a.convolve(kernel);
        System.out.println(c.toString());
        System.out.println("---------------");
        assertEquals("1D convolution - kernel: [3/A] (1)", (byte) -12, c.getShortValue(0));
        assertEquals("1D convolution - kernel: [3/A] (2)", (byte) -6, c.getShortValue(1));
        assertEquals("1D convolution - kernel: [3/A] (3)", (byte) 3, c.getShortValue(2));
        assertEquals("1D convolution - kernel: [3/A] (4)", (byte) -18, c.getShortValue(3));
        assertEquals("1D convolution - kernel: [3/A] (5)", (byte) 27, c.getShortValue(4));

        a = new ShortTensor(5);
        a.setShortValue((byte) 4, 0);
        a.setShortValue((byte) 2, 1);
        a.setShortValue((byte) -1, 2);
        a.setShortValue((byte) 6, 3);
        a.setShortValue((byte) -9, 4);

        System.out.println(a.toString());
        System.out.println("X\n");

        kernel = new ShortTensor(3);
        kernel.setShortValue((byte) -1, 0);
        kernel.setShortValue((byte) 1, 1);
        kernel.setShortValue((byte) -1, 2);

        System.out.println(kernel.toString());
        System.out.println("=\n");
        c = a.convolve(kernel);
        System.out.println(c.toString());
        System.out.println("---------------");

        assertEquals("1D convolution - kernel: [3/B] (1)", (byte) 2, c.getShortValue(0));
        assertEquals("1D convolution - kernel: [3/B] (2)", (byte) -1, c.getShortValue(1));
        assertEquals("1D convolution - kernel: [3/B] (3)", (byte) -9, c.getShortValue(2));
        assertEquals("1D convolution - kernel: [3/B] (4)", (byte) 16, c.getShortValue(3));
        assertEquals("1D convolution - kernel: [3/B] (5)", (byte) -15, c.getShortValue(4));

        a = new ShortTensor(5, 5);
        a.setShortValue((byte) 1, 0, 0);
        a.setShortValue((byte) -5, 1, 0);
        a.setShortValue((byte) 1, 2, 0);
        a.setShortValue((byte) 7, 3, 0);
        a.setShortValue((byte) 2, 4, 0);

        a.setShortValue((byte) 0, 0, 1);
        a.setShortValue((byte) 12, 1, 1);
        a.setShortValue((byte) 0, 2, 1);
        a.setShortValue((byte) -2, 3, 1);
        a.setShortValue((byte) 1, 4, 1);

        a.setShortValue((byte) 3, 0, 2);
        a.setShortValue((byte) 0, 1, 2);
        a.setShortValue((byte) 1, 2, 2);
        a.setShortValue((byte) -11, 3, 2);
        a.setShortValue((byte) 14, 4, 2);

        a.setShortValue((byte) 0, 0, 3);
        a.setShortValue((byte) -1, 1, 3);
        a.setShortValue((byte) 0, 2, 3);
        a.setShortValue((byte) 1, 3, 3);
        a.setShortValue((byte) 3, 4, 3);

        a.setShortValue((byte) -2, 0, 4);
        a.setShortValue((byte) 1, 1, 4);
        a.setShortValue((byte) 3, 2, 4);
        a.setShortValue((byte) 5, 3, 4);
        a.setShortValue((byte) -8, 4, 4);

        System.out.println(a.toString());
        System.out.println("X\n");

        kernel = new ShortTensor(3, 3);

        kernel.setShortValue((byte) -1, 0, 0);
        kernel.setShortValue((byte) 0, 1, 0);
        kernel.setShortValue((byte) -1, 2, 0);

        kernel.setShortValue((byte) 0, 0, 1);
        kernel.setShortValue((byte) 0, 1, 1);
        kernel.setShortValue((byte) 0, 2, 1);

        kernel.setShortValue((byte) -1, 0, 2);
        kernel.setShortValue((byte) 0, 1, 2);
        kernel.setShortValue((byte) -1, 2, 2);

        System.out.println(kernel.toString());
        System.out.println("=\n");
        c = a.convolve(kernel);
        System.out.println(c.toString());
        System.out.println("---------------");

        assertEquals("1D convolution - kernel: [3,3] (1)", (byte) -12, c.getShortValue(0, 0));
        assertEquals("1D convolution - kernel: [3,3] (2)", (byte) 0, c.getShortValue(1, 0));
        assertEquals("1D convolution - kernel: [3,3] (3)", (byte) -10, c.getShortValue(2, 0));
        assertEquals("1D convolution - kernel: [3,3] (4)", (byte) -1, c.getShortValue(3, 0));
        assertEquals("1D convolution - kernel: [3,3] (5)", (byte) 2, c.getShortValue(4, 0));

        assertEquals("1D convolution - kernel: [3,3] (6)", (byte) 5, c.getShortValue(0, 1));
        assertEquals("1D convolution - kernel: [3,3] (7)", (byte) -6, c.getShortValue(1, 1));
        assertEquals("1D convolution - kernel: [3,3] (8)", (byte) 9, c.getShortValue(2, 1));
        assertEquals("1D convolution - kernel: [3,3] (9)", (byte) -18, c.getShortValue(3, 1));
        assertEquals("1D convolution - kernel: [3,3] (10)", (byte) 4, c.getShortValue(4, 1));

        assertEquals("1D convolution - kernel: [3,3] (11)", (byte) -11, c.getShortValue(0, 2));
        assertEquals("1D convolution - kernel: [3,3] (12)", (byte) 0, c.getShortValue(1, 2));
        assertEquals("1D convolution - kernel: [3,3] (13)", (byte) -10, c.getShortValue(2, 2));
        assertEquals("1D convolution - kernel: [3,3] (14)", (byte) -4, c.getShortValue(3, 2));
        assertEquals("1D convolution - kernel: [3,3] (15)", (byte) 1, c.getShortValue(4, 2));

        assertEquals("1D convolution - kernel: [3,3] (16)", (byte) -1, c.getShortValue(0, 3));
        assertEquals("1D convolution - kernel: [3,3] (17)", (byte) -5, c.getShortValue(1, 3));
        assertEquals("1D convolution - kernel: [3,3] (18)", (byte) 5, c.getShortValue(2, 3));
        assertEquals("1D convolution - kernel: [3,3] (19)", (byte) -10, c.getShortValue(3, 3));
        assertEquals("1D convolution - kernel: [3,3] (20)", (byte) 6, c.getShortValue(4, 3));

        assertEquals("1D convolution - kernel: [3,3] (21)", (byte) 1, c.getShortValue(0, 4));
        assertEquals("1D convolution - kernel: [3,3] (22)", (byte) 0, c.getShortValue(1, 4));
        assertEquals("1D convolution - kernel: [3,3] (23)", (byte) 0, c.getShortValue(2, 4));
        assertEquals("1D convolution - kernel: [3,3] (24)", (byte) -3, c.getShortValue(3, 4));
        assertEquals("1D convolution - kernel: [3,3] (25)", (byte) -1, c.getShortValue(4, 4));

        a = new ShortTensor(3, 3, 3);

        a.setShortValue((byte) 1, 0, 0, 0);
        a.setShortValue((byte) 1, 1, 0, 0);
        a.setShortValue((byte) 1, 2, 0, 0);

        a.setShortValue((byte) 1, 0, 1, 0);
        a.setShortValue((byte) 1, 1, 1, 0);
        a.setShortValue((byte) 1, 2, 1, 0);

        a.setShortValue((byte) 1, 0, 2, 0);
        a.setShortValue((byte) 1, 1, 2, 0);
        a.setShortValue((byte) 1, 2, 2, 0);

        a.setShortValue((byte) 1, 0, 0, 1);
        a.setShortValue((byte) 1, 1, 0, 1);
        a.setShortValue((byte) 1, 2, 0, 1);

        a.setShortValue((byte) 1, 0, 1, 1);
        a.setShortValue((byte) 1, 1, 1, 1);
        a.setShortValue((byte) 1, 2, 1, 1);

        a.setShortValue((byte) 1, 0, 2, 1);
        a.setShortValue((byte) 1, 1, 2, 1);
        a.setShortValue((byte) 1, 2, 2, 1);

        a.setShortValue((byte) 1, 0, 0, 2);
        a.setShortValue((byte) 1, 1, 0, 2);
        a.setShortValue((byte) 1, 2, 0, 2);

        a.setShortValue((byte) 1, 0, 1, 2);
        a.setShortValue((byte) 1, 1, 1, 2);
        a.setShortValue((byte) 1, 2, 1, 2);

        a.setShortValue((byte) 1, 0, 2, 2);
        a.setShortValue((byte) 1, 1, 2, 2);
        a.setShortValue((byte) 1, 2, 2, 2);

        System.out.println(a.toString());
        System.out.println("X\n");
        kernel = new ShortTensor(3, 3, 3);

        kernel.setShortValue((byte) 1, 0, 0, 0);
        kernel.setShortValue((byte) 1, 1, 0, 0);
        kernel.setShortValue((byte) 1, 2, 0, 0);

        kernel.setShortValue((byte) 1, 0, 1, 0);
        kernel.setShortValue((byte) 1, 1, 1, 0);
        kernel.setShortValue((byte) 1, 2, 1, 0);

        kernel.setShortValue((byte) 1, 0, 2, 0);
        kernel.setShortValue((byte) 1, 1, 2, 0);
        kernel.setShortValue((byte) 1, 2, 2, 0);

        kernel.setShortValue((byte) 1, 0, 0, 1);
        kernel.setShortValue((byte) 1, 1, 0, 1);
        kernel.setShortValue((byte) 1, 2, 0, 1);

        kernel.setShortValue((byte) 1, 0, 1, 1);
        kernel.setShortValue((byte) 1, 1, 1, 1);
        kernel.setShortValue((byte) 1, 2, 1, 1);

        kernel.setShortValue((byte) 1, 0, 2, 1);
        kernel.setShortValue((byte) 1, 1, 2, 1);
        kernel.setShortValue((byte) 1, 2, 2, 1);

        kernel.setShortValue((byte) 1, 0, 0, 2);
        kernel.setShortValue((byte) 1, 1, 0, 2);
        kernel.setShortValue((byte) 1, 2, 0, 2);

        kernel.setShortValue((byte) 1, 0, 1, 2);
        kernel.setShortValue((byte) 1, 1, 1, 2);
        kernel.setShortValue((byte) 1, 2, 1, 2);

        kernel.setShortValue((byte) 1, 0, 2, 2);
        kernel.setShortValue((byte) 1, 1, 2, 2);
        kernel.setShortValue((byte) 1, 2, 2, 2);

        System.out.println(kernel.toString());
        System.out.println("=\n");
        c = a.convolve(kernel);
        System.out.println(c.toString());
        System.out.println("---------------");

        assertEquals("1D convolution - kernel: [3,3,3] (1)", (byte) 8, c.getShortValue(0, 0, 0));
        assertEquals("1D convolution - kernel: [3,3,3] (2)", (byte) 12, c.getShortValue(1, 0, 0));
        assertEquals("1D convolution - kernel: [3,3,3] (3)", (byte) 8, c.getShortValue(2, 0, 0));

        assertEquals("1D convolution - kernel: [3,3,3] (4)", (byte) 12, c.getShortValue(0, 1, 0));
        assertEquals("1D convolution - kernel: [3,3,3] (5)", (byte) 18, c.getShortValue(1, 1, 0));
        assertEquals("1D convolution - kernel: [3,3,3] (6)", (byte) 12, c.getShortValue(2, 1, 0));

        assertEquals("1D convolution - kernel: [3,3,3] (7)", (byte) 8, c.getShortValue(0, 2, 0));
        assertEquals("1D convolution - kernel: [3,3,3] (8)", (byte) 12, c.getShortValue(1, 2, 0));
        assertEquals("1D convolution - kernel: [3,3,3] (9)", (byte) 8, c.getShortValue(2, 2, 0));

        assertEquals("1D convolution - kernel: [3,3,3] (10)", (byte) 12, c.getShortValue(0, 0, 1));
        assertEquals("1D convolution - kernel: [3,3,3] (11)", (byte) 18, c.getShortValue(1, 0, 1));
        assertEquals("1D convolution - kernel: [3,3,3] (12)", (byte) 12, c.getShortValue(2, 0, 1));

        assertEquals("1D convolution - kernel: [3,3,3] (13)", (byte) 18, c.getShortValue(0, 1, 1));
        assertEquals("1D convolution - kernel: [3,3,3] (14)", (byte) 27, c.getShortValue(1, 1, 1));
        assertEquals("1D convolution - kernel: [3,3,3] (15)", (byte) 18, c.getShortValue(2, 1, 1));

        assertEquals("1D convolution - kernel: [3,3,3] (16)", (byte) 12, c.getShortValue(0, 2, 1));
        assertEquals("1D convolution - kernel: [3,3,3] (17)", (byte) 18, c.getShortValue(1, 2, 1));
        assertEquals("1D convolution - kernel: [3,3,3] (18)", (byte) 12, c.getShortValue(2, 2, 1));

        assertEquals("1D convolution - kernel: [3,3,3] (19)", (byte) 8, c.getShortValue(0, 0, 2));
        assertEquals("1D convolution - kernel: [3,3,3] (20)", (byte) 12, c.getShortValue(1, 0, 2));
        assertEquals("1D convolution - kernel: [3,3,3] (21)", (byte) 8, c.getShortValue(2, 0, 2));

        assertEquals("1D convolution - kernel: [3,3,3] (22)", (byte) 12, c.getShortValue(0, 1, 2));
        assertEquals("1D convolution - kernel: [3,3,3] (23)", (byte) 18, c.getShortValue(1, 1, 2));
        assertEquals("1D convolution - kernel: [3,3,3] (24)", (byte) 12, c.getShortValue(2, 1, 2));

        assertEquals("1D convolution - kernel: [3,3,3] (25)", (byte) 8, c.getShortValue(0, 2, 2));
        assertEquals("1D convolution - kernel: [3,3,3] (26)", (byte) 12, c.getShortValue(1, 2, 2));
        assertEquals("1D convolution - kernel: [3,3,3] (27)", (byte) 8, c.getShortValue(2, 2, 2));

    }
    
    @Test
    public void testConvolvePartial(){
        Tensor a, kernel, c;
        
        a = new ByteTensor(1);
        a.setShortValue((short) 5, 0);
        System.out.println(a.toString());
        System.out.println("X\n");
        kernel = new ByteTensor(1);
        kernel.setShortValue((short) -3, 0);
        System.out.println(kernel.toString());
        System.out.println("=\n");
        c = a.convolvePartial(kernel, 1);
        System.out.println(c.toString());
        System.out.println("---------------");
        assertEquals("0D partial convolution", (byte) -15, c.getShortValue(0));
        
        a = new ByteTensor(5);
        a.setShortValue((short) 4, 0);
        a.setShortValue((short) 2, 1);
        a.setShortValue((short) -1, 2);
        a.setShortValue((short) 6, 3);
        a.setShortValue((short) -9, 4);
        System.out.println(a.toString());
        System.out.println("X\n");
        kernel = new ByteTensor(1);
        kernel.setShortValue((byte) 2, 0);
        System.out.println(kernel.toString());
        System.out.println("=\n");
        c = a.convolvePartial(kernel, 1);
        System.out.println(c.toString());
        System.out.println("---------------");
        assertEquals("1D partial convolution - kernel: [1]", (byte) -2, c.getShortValue(0));
        
        a = new ByteTensor(5);
        a.setShortValue((short) 4, 0);
        a.setShortValue((short) 2, 1);
        a.setShortValue((short) -1, 2);
        a.setShortValue((short) 6, 3);
        a.setShortValue((short) -9, 4);
        System.out.println(a.toString());
        System.out.println("X\n");
        kernel = new ByteTensor(3);
        kernel.setShortValue((short) -3, 0);
        kernel.setShortValue((short) 1, 1);
        kernel.setShortValue((short) 2, 2);
        System.out.println(kernel.toString());
        System.out.println("=\n");
        c = a.convolvePartial(kernel, 1);
        System.out.println(c.toString());
        System.out.println("---------------");
        assertEquals("1D partial convolution - kernel: [1] (1)", (byte) 5, c.getShortValue(0));
        
        a = new ByteTensor(5);
        a.setShortValue((short) 4, 0);
        a.setShortValue((short) 2, 1);
        a.setShortValue((short) -1, 2);
        a.setShortValue((short) 6, 3);
        a.setShortValue((short) -9, 4);
        System.out.println(a.toString());
        System.out.println("X\n");
        kernel = new ByteTensor(3);
        kernel.setShortValue((short) -3, 0);
        kernel.setShortValue((short) 1, 1);
        kernel.setShortValue((short) 2, 2);
        System.out.println(kernel.toString());
        System.out.println("=\n");
        c = a.convolvePartial(kernel, 3);
        System.out.println(c.toString());
        System.out.println("---------------");
        assertEquals("1D partial convolution - kernel: [3] (1)", (short) -12, c.getShortValue(0));
        assertEquals("1D partial convolution - kernel: [3] (1)", (short) 5, c.getShortValue(1));
        assertEquals("1D partial convolution - kernel: [3] (1)", (short) -9, c.getShortValue(2));
    }
}
