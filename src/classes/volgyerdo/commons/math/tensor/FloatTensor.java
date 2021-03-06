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

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import volgyerdo.commons.primitive.ArrayUtils;
import volgyerdo.commons.primitive.ByteSupplier;
import volgyerdo.commons.primitive.PrimitiveUtils;
import volgyerdo.commons.primitive.ByteUnaryOperator;
import volgyerdo.commons.primitive.FloatSupplier;
import volgyerdo.commons.primitive.FloatUnaryOperator;
import volgyerdo.commons.primitive.ShortSupplier;
import volgyerdo.commons.primitive.ShortUnaryOperator;

/**
 *
 * @author Volgyerdo Nonprofit Kft.
 */
public class FloatTensor extends Tensor {

    public float[] values;

    public FloatTensor(int... dimensions) {
        super(TYPE.FLOAT, dimensions);
        values = new float[ArrayUtils.product(dimensions)];
    }

    @Override
    public Tensor convertTo(TYPE type) {
        return switch (type) {
            case BYTE ->
                convertToByteTensor();
            case SHORT ->
                convertToShortTensor();
            case FLOAT ->
                convertToFloatTensor();
            case OBJECT ->
                convertToObjectTensor();
            default ->
                null;
        };
    }

    private Tensor convertToByteTensor() {
        ByteTensor byteTensor = (ByteTensor) Tensor.create(TYPE.BYTE, dimensions);
        for (int i = 0; i < values.length; i++) {
            byteTensor.values[i] = PrimitiveUtils.toByte(values[i]);
        }
        return byteTensor;
    }

    private Tensor convertToShortTensor() {
        ShortTensor shortTensor = (ShortTensor) Tensor.create(TYPE.SHORT, dimensions);
        for (int i = 0; i < values.length; i++) {
            shortTensor.values[i] = PrimitiveUtils.toShort(values[i]);
        }
        return shortTensor;
    }

    private Tensor convertToFloatTensor() {
        return copy();
    }

    private Tensor convertToObjectTensor() {
        ObjectTensor objectTensor = (ObjectTensor) Tensor.create(TYPE.OBJECT, dimensions);
        for (int i = 0; i < values.length; i++) {
            objectTensor.values[i] = values[i];
        }
        return objectTensor;
    }

    @Override
    public int size() {
        return values.length;
    }

    @Override
    public void set(Tensor tensor) {
        checkNull(tensor);
        checkClass(tensor);
        checkDimensionCount(tensor.dimensions);
        checkDimensions(tensor);
        System.arraycopy(((FloatTensor) tensor).values, 0, values, 0, values.length);
    }

    @Override
    public void setFloatValue(float value, int... indices) {
        values[index(indices)] = value;
    }

    @Override
    public void setByteValue(byte value, int... indices) {
        values[index(indices)] = value;
    }

    @Override
    public void setShortValue(short value, int... indices) {
        values[index(indices)] = value;
    }

    @Override
    public void setObjectValue(Object value, int... indices) {
        throw new RuntimeException("Can't store an object in a byte tensor.");
    }

    @Override
    public byte getByteValue(int... indices) {
        return PrimitiveUtils.toByte(values[index(indices)]);
    }

    @Override
    public short getShortValue(int... indices) {
        return PrimitiveUtils.toShort(values[index(indices)]);
    }

    @Override
    public float getFloatValue(int... indices) {
        return values[index(indices)];
    }

    @Override
    public Object getObjectValue(int... indices) {
        throw new RuntimeException("Can't get an object from a tensor.");
    }

    @Override
    public void setByteArray(byte[] values) {
        if (dimensions.length != 1 || dimensions[0] != values.length) {
            throw new IllegalArgumentException("Array dimension is different.");
        }
        for (int i = 0; i < values.length; i++) {
            this.values[i] = values[i];
        }
    }

    @Override
    public void setShortArray(short[] values) {
        if (dimensions.length != 1 || dimensions[0] != values.length) {
            throw new IllegalArgumentException("Array dimension is different.");
        }
        for (int i = 0; i < values.length; i++) {
            this.values[i] = values[i];
        }
    }

    @Override
    public void setFloatArray(float[] values) {
        if (dimensions.length != 1 || dimensions[0] != values.length) {
            throw new IllegalArgumentException("Array dimension is different.");
        }
        System.arraycopy(values, 0, this.values, 0, values.length);
    }

    @Override
    public void setObjectArray(Object[] values) {
        throw new RuntimeException("Can't set an object array into a byte tensor.");
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Arrays.hashCode(this.values);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FloatTensor other = (FloatTensor) obj;
        if (!Arrays.equals(this.values, other.values)) {
            return false;
        }
        return true;
    }

    @Override
    public void randomize(byte min, byte max) {
        randomize((float) min, (float) max);
    }

    @Override
    public void randomize(short min, short max) {
        randomize((float) min, (float) max);
    }

    @Override
    public void randomize(float min, float max) {
        if (max < min) {
            throw new RuntimeException("Max < min in randomize parameters.");
        }
        ArrayUtils.randomize(values, min, max);
    }

    @Override
    public void fill(byte x) {
        for (int i = 0; i < values.length; i++) {
            values[i] = x;
        }
    }

    @Override
    public void fill(short x) {
        for (int i = 0; i < values.length; i++) {
            values[i] = x;
        }
    }

    @Override
    public void fill(float x) {
        for (int i = 0; i < values.length; i++) {
            values[i] = x;
        }
    }

    @Override
    public void add(byte x) {
        add((float) x);
    }

    @Override
    public void add(short x) {
        add((float) x);
    }

    @Override
    public void add(float x) {
        for (int i = 0; i < values.length; i++) {
            values[i] += x;
        }
    }

    @Override
    public void add(Tensor tensor) {
        checkNull(tensor);
        checkClass(tensor);
        checkDimensionCount(tensor.dimensions);
        checkDimensions(tensor);
        for (int i = 0; i < values.length; i++) {
            values[i] += ((FloatTensor) tensor).values[i];
        }
    }

    @Override
    public void substract(byte x) {
        substract((float) x);
    }

    @Override
    public void substract(short x) {
        substract((float) x);
    }

    @Override
    public void substract(float x) {
        for (int i = 0; i < values.length; i++) {
            values[i] -= x;
        }
    }

    @Override
    public void substract(Tensor tensor) {
        checkNull(tensor);
        checkClass(tensor);
        checkDimensionCount(tensor.dimensions);
        checkDimensions(tensor);
        for (int i = 0; i < values.length; i++) {
            values[i] -= ((FloatTensor) tensor).values[i];
        }
    }

    @Override
    public void multiply(byte x) {
        multiply((float) x);
    }

    @Override
    public void multiply(short x) {
        multiply((float) x);
    }

    @Override
    public void multiply(float x) {
        for (int i = 0; i < values.length; i++) {
            values[i] *= x;
        }
    }

    @Override
    public void divide(byte x) {
        divide((float) x);
    }

    @Override
    public void divide(short x) {
        divide((float) x);
    }

    @Override
    public void divide(float x) {
        for (int i = 0; i < values.length; i++) {
            values[i] /= x;
        }
    }

    @Override
    public Tensor sum() {
        double sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
        }
        Tensor result = Tensor.create(type, 1);
        result.setFloatValue(PrimitiveUtils.toFloat(sum), 0);
        return result;
    }

    @Override
    public byte byteSum() {
        return PrimitiveUtils.toByte(doubleSum());
    }

    @Override
    public short shortSum() {
        return PrimitiveUtils.toShort(doubleSum());
    }

    @Override
    public float floatSum() {
        return PrimitiveUtils.toFloat(doubleSum());
    }

    private double doubleSum() {
        double sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
        }
        return sum;
    }

    @Override
    public byte byteMin() {
        return PrimitiveUtils.toByte(doubleMin());
    }

    @Override
    public short shortMin() {
        return PrimitiveUtils.toShort(doubleMin());
    }

    @Override
    public float floatMin() {
        return PrimitiveUtils.toFloat(doubleMin());
    }

    private double doubleMin() {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < values.length; i++) {
            if (min > values[i]) {
                min = values[i];
            }
        }
        return min;
    }

    @Override
    public byte byteMax() {
        return PrimitiveUtils.toByte(doubleMax());
    }

    @Override
    public short shortMax() {
        return PrimitiveUtils.toShort(doubleMax());
    }

    @Override
    public float floatMax() {
        return PrimitiveUtils.toFloat(doubleMax());
    }

    private double doubleMax() {
        double max = Double.MIN_VALUE;
        for (int i = 0; i < values.length; i++) {
            if (max < values[i]) {
                max = values[i];
            }
        }
        return max;
    }

    @Override
    public byte byteAverage() {
        return PrimitiveUtils.toByte(doubleAverage());
    }

    @Override
    public short shortAverage() {
        return PrimitiveUtils.toShort(doubleAverage());
    }

    @Override
    public float floatAverage() {
        return PrimitiveUtils.toFloat(doubleAverage());
    }

    private double doubleAverage() {
        double average = 0;
        for (int i = 0; i < values.length; i++) {
            average += values[i];
        }
        average /= values.length;
        return average;
    }

    @Override
    public void processByte(ByteUnaryOperator operator) {
        for (int i = 0; i < values.length; i++) {
            values[i] = operator.applyAsByte(PrimitiveUtils.toByte(values[i]));
        }
    }

    @Override
    public void processShort(ShortUnaryOperator operator) {
        for (int i = 0; i < values.length; i++) {
            values[i] = operator.applyAsShort(PrimitiveUtils.toShort(values[i]));
        }
    }

    @Override
    public void processFloat(FloatUnaryOperator operator) {
        for (int i = 0; i < values.length; i++) {
            values[i] = operator.applyAsFloat(values[i]);
        }
    }

    @Override
    public void processObject(UnaryOperator operator) {
        throw new RuntimeException("Float tensor doesn't have object processor function.");
    }

    @Override
    public void fillWithByte(ByteSupplier operator) {
        for (int i = 0; i < values.length; i++) {
            values[i] = operator.getAsByte();
        }
    }

    @Override
    public void fillWithShort(ShortSupplier operator) {
        for (int i = 0; i < values.length; i++) {
            values[i] = operator.getAsShort();
        }
    }

    @Override
    public void fillWithFloat(FloatSupplier operator) {
        for (int i = 0; i < values.length; i++) {
            values[i] = operator.getAsFloat();
        }
    }

    @Override
    public void fillWithObject(Supplier operator) {
        throw new RuntimeException("Float tensor doesn't have object filler function.");
    }

    @Override
    public void negate() {
        for (int i = 0; i < values.length; i++) {
            values[i] = (float) -values[i];
        }
    }

    @Override
    public void abs() {
        for (int i = 0; i < values.length; i++) {
            values[i] = values[i] >= 0 ? values[i] : (float) -values[i];
        }
    }

    @Override
    public Tensor transpose() {
        FloatTensor transposed = (FloatTensor) Tensor.create(TYPE.FLOAT, ArrayUtils.reverse(dimensions));
        int[] indices = new int[dimensions.length];
        Arrays.fill(indices, 0);
        transposeRecursive(transposed, 0, indices);
        return transposed;
    }

    private void transposeRecursive(FloatTensor tensor, int current, int[] indices) {
        if (current == indices.length) {
            tensor.setFloatValue(getFloatValue(indices), ArrayUtils.reverse(indices));
        } else {
            int next = current + 1;
            for (int i = 0; i < dimensions[current]; i++) {
                indices[current] = i;
                transposeRecursive(tensor, next, indices);
            }
        }
    }

    @Override
    public Tensor rotate() {
        FloatTensor flipped = (FloatTensor) createSimilar();
        int[] indices = new int[dimensions.length];
        Arrays.fill(indices, 0);
        rotateRecursive(flipped, 0, indices);
        return flipped;
    }

    private void rotateRecursive(FloatTensor tensor, int current, int[] indices) {
        if (current == indices.length) {
            tensor.setFloatValue(getFloatValue(indices), reverseIndex(indices));
        } else {
            int next = current + 1;
            for (int i = 0; i < dimensions[current]; i++) {
                indices[current] = i;
                rotateRecursive(tensor, next, indices);
            }
        }
    }

    @Override
    public void hadamardProduct(Tensor multiplier) {
        checkNull(multiplier);
        checkClass(multiplier);
        checkDimensionCount(multiplier.dimensions);
        checkDimensions(multiplier);
        for (int i = 0; i < values.length; i++) {
            values[i] = values[i] * ((FloatTensor) multiplier).values[i];
        }
    }

    @Override
    protected void sumProductRecursive(Tensor multiplier, Tensor target,
            int[] commonDimensions, int[] multiplierDimensions, int[] outputDimensions,
            int depth, int[] pos, int n, int[] indices, int[] rd1, int[] rd2) {
        if (n < commonDimensions.length) {
            for (int i = 0; i < commonDimensions[n]; i++) {
                indices[n] = i;
                sumProductRecursive(multiplier, target, commonDimensions,
                        multiplierDimensions, outputDimensions, depth, pos, n + 1, indices, rd1, rd2);
            }
        } else {
            System.arraycopy(indices, 0, rd1, dimensions.length - depth, depth);
            System.arraycopy(indices, 0, rd2, 0, depth);
            target.setFloatValue(target.getFloatValue(pos)
                    + getFloatValue(rd1) * multiplier.getFloatValue(rd2), pos);
        }
    }

    @Override
    protected void convolveRecursive(Tensor kernel, Tensor result, int k, int[] d) {
        if (k < dimensions.length) {
            for (int i = 0; i < dimensions[k]; i++) {
                d[k] = i;
                convolveRecursive(kernel, result, k + 1, d);
            }
        } else {
            result.setFloatValue(convolutionSum(kernel, d, 0, new int[kernel.dimensions.length]), d);
        }
    }

    private float convolutionSum(Tensor kernel, int[] d, int k, int[] e) {
        if (k < kernel.dimensions.length) {
            float s = 0;
            for (int i = 0; i < kernel.dimensions[k]; i++) {
                e[k] = i;
                s += convolutionSum(kernel, d, k + 1, e);
            }
            return s;
        } else {
            int[] rd = new int[dimensions.length];
            for (int i = 0; i < dimensions.length; i++) {
                rd[i] = d[i] + e[i] - kernel.dimensions[i] / 2;
                if (rd[i] < 0 || rd[i] > dimensions[i] - 1) {
                    return 0;
                }
            }
            return getFloatValue(rd) * kernel.getFloatValue(e);
        }
    }

    @Override
    protected void convolvePartialRecursive(Tensor kernel, Tensor result, int k, int[] kd, int[] d) {
        if (k < result.dimensions.length) {
            for (int i = 0; i < result.dimensions[k]; i++) {
                kd[k] = i;
                d[k] = dimensions[k] / 2 + i - result.dimensions[k] / 2;
                convolvePartialRecursive(kernel, result, k + 1, kd, d);
            }
        } else {
            result.setFloatValue(partialConvolutionSum(kernel, d, 0, new int[kernel.dimensions.length]), kd);
        }
    }

    private float partialConvolutionSum(Tensor kernel, int[] d, int k, int[] e) {
        if (k < kernel.dimensions.length) {
            float s = 0;
            for (int i = 0; i < kernel.dimensions[k]; i++) {
                e[k] = i;
                s += convolutionSum(kernel, d, k + 1, e);
            }
            return s;
        } else {
            int[] rd = new int[dimensions.length];
            for (int i = 0; i < dimensions.length; i++) {
                rd[i] = d[i] + e[i] - kernel.dimensions[i] / 2;
                if (rd[i] < 0 || rd[i] > dimensions[i] - 1) {
                    return 0;
                }
            }
            return getFloatValue(rd) * kernel.getFloatValue(e);
        }
    }

    @Override
    public Tensor copy() {
        FloatTensor copy = new FloatTensor(dimensions);
        System.arraycopy(values, 0, copy.values, 0, values.length);
        return copy;
    }

    @Override
    public IndexIterator indexIterator() {
        return new IndexIterator(dimensions);
    }

    @Override
    public void toStringRecursive(StringBuilder sb, int n, int[] indices, boolean newLine) {
        if (n < indices.length) {
            for (int i = 0; i < dimensions[n]; i++) {
                indices[n] = i;
                toStringRecursive(sb, n + 1, indices, newLine);
            }
            if (newLine) {
                sb.append("\n");
            }
        } else {
            sb.append("[").append(getFloatValue(indices)).append("]");
        }
    }
}
