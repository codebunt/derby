/*

   Licensed Materials - Property of IBM
   Cloudscape - Package org.apache.derby.iapi.services.io
   (C) Copyright IBM Corp. 2001, 2004. All Rights Reserved.
   US Government Users Restricted Rights - Use, duplication or
   disclosure restricted by GSA ADP Schedule Contract with IBM Corp.

 */
package org.apache.derby.iapi.services.io;

import java.io.*;
import org.apache.derby.iapi.services.io.AccessibleByteArrayOutputStream;


class DebugByteTeeOutputStream extends FilterOutputStream {

	/**
		IBM Copyright &copy notice.
	*/
	public static final String copyrightNotice = org.apache.derby.iapi.reference.Copyright.SHORT_2001_2004;
	private AccessibleByteArrayOutputStream tee = new AccessibleByteArrayOutputStream(256);

	DebugByteTeeOutputStream(OutputStream out) {
		super(out);
	}
	
	public void write(int b) throws IOException {
		out.write(b);
		tee.write(b);
	}

	public void write(byte[] b, int off, int len) throws IOException {

		out.write(b,off,len);
		tee.write(b,off,len);
	}


	void checkObject(Formatable f) {

		ByteArrayInputStream in = new ByteArrayInputStream(tee.getInternalByteArray(), 0, tee.size());

		FormatIdInputStream fin = new FormatIdInputStream(in);

		// now get an empty object given the format identification
		// read it in
		// then compare it???

		Formatable f1 = null;
		try {

			f1 = (Formatable) fin.readObject();

			if (f1.equals(f)) {
				return;
			}

			// If the two objects are not equal and it looks
			// like they don't implement their own equals()
			// (which requires a matching hashCode() then
			// just return. The object was read sucessfully.

			if ((f1.hashCode() == System.identityHashCode(f1)) &&
				(f.hashCode() == System.identityHashCode(f)))
				return;
		} catch (Throwable t) {
			System.out.println("FormatableError:read error    : " + t.toString());
			System.out.println("FormatableError:class written : " + f.getClass());
            if( null == f1)
                System.out.println("FormatableError:read back as null");
            else
                System.out.println("FormatableError:class read    : " + f1.getClass());
			System.out.println("FormatableError:write id      : " + FormatIdUtil.formatIdToString(f.getTypeFormatId()));
            if( null != f1)
                System.out.println("FormatableError:read id       : " + FormatIdUtil.formatIdToString(f1.getTypeFormatId()));
			t.printStackTrace(System.out);
		}

		//System.out.println("FormatableError:Class written " + f.getClass() + " format id " + f.getTypeFormatId());
		//if (f1 != null)
			//System.out.println("FormatableError:Class read    " + f1.getClass() + " format id " + f1.getTypeFormatId());
	}

}
