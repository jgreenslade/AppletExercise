package edu.elon.io;

import java.io.FilterWriter;
import java.io.Writer;

public class EncryptWriter extends FilterWriter {

	protected EncryptWriter(Writer out) {
		super(out);
	}

}
