package edu.elon.io;

import java.io.FilterReader;
import java.io.Reader;

public class DecryptReader extends FilterReader {

	protected DecryptReader(Reader in) {
		super(in);
		
	}

}
