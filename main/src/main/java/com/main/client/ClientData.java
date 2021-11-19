package com.main.client;

//io
import java.io.IOException;

import java.io.InputStream;
import java.io.OutputStream;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class ClientData {
    public final InputStream in;
    public final OutputStream out;

    public final BufferedReader inReader;
    public final BufferedWriter outWriter;

    public final String clientName;

    public ClientData(InputStream clientIn, OutputStream clientOut) {
        this.in = clientIn;
        this.out = clientOut;

        InputStreamReader clientInputStreamWriter = new InputStreamReader(clientIn);
        inReader = new BufferedReader(clientInputStreamWriter);

        OutputStreamWriter clientOutputStreamWriter = new OutputStreamWriter(clientOut);
        outWriter = new BufferedWriter(clientOutputStreamWriter);

        clientName = getClientName();
    }

    private String getClientName() {
        try {
            return inReader.readLine();
        } catch (IOException error) {
            System.out.println(error.getMessage());
            return "Name not recieved";
        }
    }

    @Override
    protected void finalize() throws Throwable {
        in.close();
        out.close();

        inReader.close();
        outWriter.close();
   }
}
