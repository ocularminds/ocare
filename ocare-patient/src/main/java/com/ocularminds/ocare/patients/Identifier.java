/*
 * Copyright (c) 2016-2017 Ocular-Minds Software Limited
 * 
 * Permission is hereby granted to Vatebra Limited, to any person representing Vatebra in a copy of
 * this software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to
 * whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY FOR 6-MONTH SUPPORT AFTER INITIAL HANDING OVER
 * OF THE SOURCE CODES
 */
package com.ocularminds.ocare;

/**
 * Identifier generates a random Id with varying sizes
 *
 * @author Festus B. Jejelowo
 */
public final class Identifier {

    private Type type;

    public enum Type {
        LONG, MIN, SHORT;
    }

    public Identifier() {
        this(Type.SHORT);
    }

    public Identifier(final Type typ) {
        type = typ;
    }

    public String next() throws Exception {
        return IdGenerator.getInstance().generate(type);
    }
}
