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
 * VectorDataItem.java
 * -------------------
 * (C) Copyright 2007-2022, by David Gilbert.
 *
 * Original Author:  David Gilbert;
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 30-Jan-2007 : Version 1 (DG);
 * 24-May-2007 : Added getVector(), renamed getDeltaX() --> getVectorX(),
 *               and likewise for getDeltaY() (DG);
 * 25-May-2007 : Moved from experimental to the main source tree (DG);
 *
 */

package org.jfree.data.xy;

import java.io.Serializable;

import org.jfree.data.ComparableObjectItem;

/**
 * A data item representing data in the form (x, y, deltaX, deltaY), intended
 * for use by the {@link VectorSeries} class.
 *
 * @since 1.0.6
 */
public class VectorDataItem extends ComparableObjectItem {

    /**
     * Creates a new {@code VectorDataItem} instance.
     *
     * @param x  the x-value.
     * @param y  the y-value.
     * @param deltaX  the vector x.
     * @param deltaY  the vector y.
     */
    public VectorDataItem(double x, double y, double deltaX, double deltaY) {
        super(new XYCoordinate(x, y), new Vector(deltaX, deltaY));
    }

    /**
     * Returns the x-value.
     *
     * @return The x-value (never {@code null}).
     */
    public double getXValue() {
        XYCoordinate xy = (XYCoordinate) getComparable();
        return xy.getX();
    }

    /**
     * Returns the y-value.
     *
     * @return The y-value.
     */
    public double getYValue() {
        XYCoordinate xy = (XYCoordinate) getComparable();
        return xy.getY();
    }

    /**
     * Returns the vector.
     *
     * @return The vector (possibly {@code null}).
     */
    public Vector getVector() {
        return (Vector) getObject();
    }

    /**
     * Returns the x-component for the vector.
     *
     * @return The x-component.
     */
    public double getVectorX() {
        Vector vi = (Vector) getObject();
        if (vi != null) {
            return vi.getX();
        }
        else {
            return Double.NaN;
        }
    }

    /**
     * Returns the y-component for the vector.
     *
     * @return The y-component.
     */
    public double getVectorY() {
        Vector vi = (Vector) getObject();
        if (vi != null) {
            return vi.getY();
        }
        else {
            return Double.NaN;
        }
    }

    static class XYCoordinate implements Comparable, Serializable {

        /** The x-coordinate. */
        private double x;

        /** The y-coordinate. */
        private double y;

        /**
         * Creates a new coordinate for the point (0.0, 0.0).
         */
        public XYCoordinate() {
            this(0.0, 0.0);
        }

        /**
         * Creates a new coordinate for the point (x, y).
         *
         * @param x  the x-coordinate.
         * @param y  the y-coordinate.
         */
        public XYCoordinate(double x, double y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Returns the x-coordinate.
         *
         * @return The x-coordinate.
         */
        public double getX() {
            return this.x;
        }

        /**
         * Returns the y-coordinate.
         *
         * @return The y-coordinate.
         */
        public double getY() {
            return this.y;
        }

        /**
         * Tests this coordinate for equality with an arbitrary object.
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
            if (!(obj instanceof XYCoordinate)) {
                return false;
            }
            XYCoordinate that = (XYCoordinate) obj;
            if (this.x != that.x) {
                return false;
            }
            if (this.y != that.y) {
                return false;
            }
            return true;
        }

        /**
         * Returns a hash code for this instance.
         *
         * @return A hash code.
         */
        @Override
        public int hashCode() {
            int result = 193;
            long temp = Double.doubleToLongBits(this.x);
            result = 37 * result + (int) (temp ^ (temp >>> 32));
            temp = Double.doubleToLongBits(this.y);
            result = 37 * result + (int) (temp ^ (temp >>> 32));
            return result;
        }

        /**
         * Returns a string representation of this instance, primarily for
         * debugging purposes.
         *
         * @return A string.
         */
        @Override
        public String toString() {
            return "(" + this.x + ", " + this.y + ")";
        }

        /**
         * Compares this instance against an arbitrary object.
         *
         * @param obj  the object ({@code null} not permitted).
         *
         * @return An integer indicating the relative order of the items.
         */
        @Override
        public int compareTo(Object obj) {
            if (!(obj instanceof XYCoordinate)) {
                throw new IllegalArgumentException("Incomparable object.");
            }
            XYCoordinate that = (XYCoordinate) obj;
            if (this.x > that.x) {
                return 1;
            }
            else if (this.x < that.x) {
                return -1;
            }
            else {
                if (this.y > that.y) {
                    return 1;
                }
                else if (this.y < that.y) {
                    return -1;
                }
            }
            return 0;
        }

    }


}
