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
 * ----------------------
 * XIntervalDataItem.java
 * ----------------------
 * (C) Copyright 2006-2022, by David Gilbert.
 *
 * Original Author:  David Gilbert;
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 20-Oct-2006 : Version 1 (DG);
 *
 */

package org.jfree.data.xy;

import org.jfree.data.ComparableObjectItem;

import java.io.Serializable;

/**
 * An item representing data in the form (x, x-low, x-high, y).
 */
public class XIntervalDataItem extends ComparableObjectItem {

    /**
     * Creates a new instance of {@code XIntervalDataItem}.
     *
     * @param x  the x-value.
     * @param xLow  the lower bound of the x-interval.
     * @param xHigh  the upper bound of the x-interval.
     * @param y  the y-value.
     */
    public XIntervalDataItem(double x, double xLow, double xHigh, double y) {
        super(x, new YWithXInterval(y, xLow, xHigh));
    }

    /**
     * Returns the x-value.
     *
     * @return The x-value (never {@code null}).
     */
    public Number getX() {
        return (Number) getComparable();
    }

    /**
     * Returns the y-value.
     *
     * @return The y-value.
     */
    public double getYValue() {
        YWithXInterval interval = (YWithXInterval) getObject();
        if (interval != null) {
            return interval.getY();
        } else {
            return Double.NaN;
        }
    }

    /**
     * Returns the lower bound of the x-interval.
     *
     * @return The lower bound of the x-interval.
     */
    public double getXLowValue() {
        YWithXInterval interval = (YWithXInterval) getObject();
        if (interval != null) {
            return interval.getXLow();
        } else {
            return Double.NaN;
        }
    }

    /**
     * Returns the upper bound of the x-interval.
     *
     * @return The upper bound of the x-interval.
     */
    public double getXHighValue() {
        YWithXInterval interval = (YWithXInterval) getObject();
        if (interval != null) {
            return interval.getXHigh();
        } else {
            return Double.NaN;
        }
    }

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
     * -------------------
     * YWithXInterval.java
     * -------------------
     * (C) Copyright 2006-2022, by David Gilbert.
     *
     * Original Author:  David Gilbert;
     * Contributor(s):   -;
     *
     */

    /**
     * A y-value plus the bounds for the related x-interval.  This curious
     * combination exists as an implementation detail, to fit into the structure
     * of the ComparableObjectSeries class.  It would have been possible to
     * simply reuse the {@link YInterval} class by assuming that the y-interval
     * in fact represents the x-interval, however I decided it was better to
     * duplicate some code in order to document the real intent.
     */
    static class YWithXInterval implements Serializable {

        /** The y-value. */
        private double y;

        /** The lower bound of the x-interval. */
        private double xLow;

        /** The upper bound of the x-interval. */
        private double xHigh;

        /**
         * Creates a new instance of {@code YWithXInterval}.
         *
         * @param y  the y-value.
         * @param xLow  the lower bound of the x-interval.
         * @param xHigh  the upper bound of the x-interval.
         */
        public YWithXInterval(double y, double xLow, double xHigh) {
            this.y = y;
            this.xLow = xLow;
            this.xHigh = xHigh;
        }

        /**
         * Returns the y-value.
         *
         * @return The y-value.
         */
        public double getY() {
            return this.y;
        }

        /**
         * Returns the lower bound of the x-interval.
         *
         * @return The lower bound of the x-interval.
         */
        public double getXLow() {
            return this.xLow;
        }

        /**
         * Returns the upper bound of the x-interval.
         *
         * @return The upper bound of the x-interval.
         */
        public double getXHigh() {
            return this.xHigh;
        }

        /**
         * Tests this instance for equality with an arbitrary object.
         *
         * @param obj  the object ({@code null} permitted).
         *
         * @return A boolean.
         */
        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof YWithXInterval)) {
                return false;
            }
            YWithXInterval that = (YWithXInterval) obj;
            if (this.y != that.y) {
                return false;
            }
            if (this.xLow != that.xLow) {
                return false;
            }
            if (this.xHigh != that.xHigh) {
                return false;
            }
            return true;
        }

    }


}
