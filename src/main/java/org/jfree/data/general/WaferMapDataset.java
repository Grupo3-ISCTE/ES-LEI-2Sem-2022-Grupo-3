/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2022, by David Gilbert and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates. 
 * Other names may be trademarks of their respective owners.]
 *
 * --------------------
 * WaferMapDataset.java
 * --------------------
 * (C)opyright 2003-2020, by Robert Redburn and Contributors.
 *
 * Original Author:  Robert Redburn;
 * Contributor(s):   David Gilbert;
 *
 */

package org.jfree.data.general;

import org.jfree.data.DefaultKeyedValues2D;

/**
 * A dataset that can be used with the {@link org.jfree.chart.plot.WaferMapPlot}
 * class.
 */
public class WaferMapDataset extends AbstractDataset {

    /**
     * Storage structure for the data values (row key is chipx, column is
     * chipy)
     */
    public DefaultKeyedValues2D data;

    /** wafer x dimension */
    private int maxChipX;

    /** wafer y dimension */
    private int maxChipY;

    /** space to draw between chips */
    private double chipSpace;

    /** maximum value in this dataset */
    private Double maxValue;

    /** minimum value in this dataset */
    private Double minValue;

    /** default chip spacing */
    private static final double DEFAULT_CHIP_SPACE = 1d;

    /**
     * Creates a new dataset using the default chipspace.
     *
     * @param maxChipX  the wafer x-dimension.
     * @param maxChipY  the wafer y-dimension.
     */
    public WaferMapDataset(int maxChipX, int maxChipY) {
        this(maxChipX, maxChipY, null);
    }

    /**
     * Creates a new dataset.
     *
     * @param maxChipX  the wafer x-dimension.
     * @param maxChipY  the wafer y-dimension.
     * @param chipSpace  the space between chips.
     */
    public WaferMapDataset(int maxChipX, int maxChipY, Number chipSpace) {

        this.maxValue = Double.NEGATIVE_INFINITY;
        this.minValue = Double.POSITIVE_INFINITY;
        this.data = new DefaultKeyedValues2D();

        this.maxChipX = maxChipX;
        this.maxChipY = maxChipY;
        if (chipSpace == null) {
            this.chipSpace = DEFAULT_CHIP_SPACE;
        }
        else {
            this.chipSpace = chipSpace.doubleValue();
        }

    }

    /**
     * Sets a value in the dataset.
     *
     * @param value  the value.
     * @param chipx  the x-index for the chip.
     * @param chipy  the y-index for the chip.
     */
    public void addValue(Number value, Comparable chipx, Comparable chipy) {
        setValue(value, chipx, chipy);
    }

    /**
     * Adds a value to the dataset.
     *
     * @param v  the value.
     * @param x  the x-index.
     * @param y  the y-index.
     */
    public void addValue(int v, int x, int y) {
        setValue((double) v, x, y);
    }

    /**
     * Sets a value in the dataset and updates min and max value entries.
     *
     * @param value  the value.
     * @param chipx  the x-index.
     * @param chipy  the y-index.
     */
    public void setValue(Number value, Comparable chipx, Comparable chipy) {
        this.data.setValue(value, chipx, chipy);
        if (isMaxValue(value)) {
            this.maxValue = (Double) value;
        }
        if (isMinValue(value)) {
            this.minValue = (Double) value;
        }
    }

    /**
     * Returns the number of unique values.
     *
     * @return The number of unique values.
     */
    public int getUniqueValueCount() {
        return data.getUniqueValues(this).size();
    }

    /**
     * Returns the data value for a chip.
     *
     * @param chipx  the x-index.
     * @param chipy  the y-index.
     *
     * @return The data value.
     */
    public Number getChipValue(int chipx, int chipy) {
        return data.getChipValue(Integer.valueOf(chipx), Integer.valueOf(chipy));
    }

    /**
     * Tests to see if the passed value is larger than the stored maxvalue.
     *
     * @param check  the number to check.
     *
     * @return A boolean.
     */
    public boolean isMaxValue(Number check) {
        if (check.doubleValue() > this.maxValue) {
            return true;
        }
        return false;
    }

    /**
     * Tests to see if the passed value is smaller than the stored minvalue.
     *
     * @param check  the number to check.
     *
     * @return A boolean.
     */
    public boolean isMinValue(Number check) {
        if (check.doubleValue() < this.minValue) {
            return true;
        }
        return false;
    }

    /**
     * Returns the maximum value stored in the dataset.
     *
     * @return The maximum value.
     */
    public Number getMaxValue() {
        return this.maxValue;
    }

    /**
     * Returns the minimum value stored in the dataset.
     *
     * @return The minimum value.
     */
    public Number getMinValue() {
        return this.minValue;
    }

    /**
     * Returns the wafer x-dimension.
     *
     * @return The number of chips in the x-dimension.
     */
    public int getMaxChipX() {
        return this.maxChipX;
    }

    /**
     * Sets wafer x dimension.
     *
     * @param maxChipX  the number of chips in the x-dimension.
     */
    public void setMaxChipX(int maxChipX) {
        this.maxChipX = maxChipX;
    }

    /**
     * Returns the number of chips in the y-dimension.
     *
     * @return The number of chips.
     */
    public int getMaxChipY() {
        return this.maxChipY;
    }

    /**
     * Sets the number of chips in the y-dimension.
     *
     * @param maxChipY  the number of chips.
     */
    public void setMaxChipY(int maxChipY) {
        this.maxChipY = maxChipY;
    }

    /**
     * Returns the space to draw between chips.
     *
     * @return The space.
     */
    public double getChipSpace() {
        return this.chipSpace;
    }

    /**
     * Sets the space to draw between chips.
     *
     * @param space  the space.
     */
    public void setChipSpace(double space) {
        this.chipSpace = space;
    }

}
