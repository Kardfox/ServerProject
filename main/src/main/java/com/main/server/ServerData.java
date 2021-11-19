package com.main.server;

//io
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.OutputStream;
import java.io.OutputStreamWriter;

import java.io.BufferedWriter;
import java.io.BufferedReader;


public class ServerData {
    public final InputStream in;
    public final OutputStream out;

    public final BufferedReader inReader;
    public final BufferedWriter outWriter;

    public ServerData(InputStream serverIn, OutputStream serverOut) {
        this.in = serverIn;
        this.out = serverOut;

        InputStreamReader serverInputStreamWriter = new InputStreamReader(serverIn);
        inReader = new BufferedReader(serverInputStreamWriter);

        OutputStreamWriter serverOutputStreamWriter = new OutputStreamWriter(serverOut);
        outWriter = new BufferedWriter(serverOutputStreamWriter);
    }
}
