package org.example;

import org.apache.thrift.TException;

public class MultiplicationHandler implements MultiplicationService.Iface {

    @Override
    public int multiply(int n1, int n2) throws TException {
        System.out.println("Processing multiplication("+n1+", "+n2+")");
        return n1*n2;
    }
}
