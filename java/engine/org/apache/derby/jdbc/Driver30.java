/*

   Licensed Materials - Property of IBM
   Cloudscape - Package org.apache.derby.jdbc
   (C) Copyright IBM Corp. 2001, 2004. All Rights Reserved.
   US Government Users Restricted Rights - Use, duplication or
   disclosure restricted by GSA ADP Schedule Contract with IBM Corp.

 */

package org.apache.derby.jdbc;

import org.apache.derby.impl.jdbc.EmbedConnection;

import org.apache.derby.iapi.services.sanity.SanityManager;

import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.error.StandardException;
import org.apache.derby.iapi.jdbc.BrokeredConnection;
import org.apache.derby.iapi.jdbc.BrokeredConnection30;
import org.apache.derby.iapi.jdbc.BrokeredConnectionControl;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.derby.impl.jdbc.*;

import java.util.Properties;

/**
	This class extends the local20 JDBC driver in order to determine at JBMS
	boot-up if the JVM that runs us does support JDBC 3.0. If it is the case
	then we will load the appropriate class(es) that have JDBC 3.0 new public
	methods and sql types.
 
*/

public class Driver30 extends Driver20 {

	/**
		IBM Copyright &copy notice.
	*/
	public static final String copyrightNotice = org.apache.derby.iapi.reference.Copyright.SHORT_2001_2004;

	/**
 	 * Get a new nested connection.
	 *
	 * @param conn	The EmbedConnection.
	 *
	 * @return A nested connection object.
	 *
	 */
	public Connection getNewNestedConnection(EmbedConnection conn)
	{
		if (SanityManager.DEBUG)
		{
			SanityManager.ASSERT(conn instanceof EmbedConnection30,
				"conn expected to be instanceof EmbedConnection30");
		}
		return new EmbedConnection30(conn);
	}

	/*
		Methods to be overloaded in sub-implementations such as
		a tracing driver.
  */
	public EmbedConnection getNewEmbedConnection(String url, Properties info)
		 throws SQLException 
	{
		return new EmbedConnection30(this, url, info);
	}

	/**
	 	@exception SQLException if fails to create statement
	 */
	public java.sql.PreparedStatement
	newEmbedPreparedStatement (
			EmbedConnection conn,
			String stmt,
			boolean forMetaData,
			int resultSetType,
			int resultSetConcurrency,
			int resultSetHoldability,
			int autoGeneratedKeys,
			int[] columnIndexes,
			String[] columnNames)
    throws SQLException
	{
		return new EmbedPreparedStatement30(conn,
								stmt,
								forMetaData,
								resultSetType,
								resultSetConcurrency,
								resultSetHoldability,
								autoGeneratedKeys,
								columnIndexes,
								columnNames);
	}

	/**
	 	@exception SQLException if fails to create statement
	 */
	public java.sql.CallableStatement
	newEmbedCallableStatement(
			EmbedConnection conn,
			String stmt,
			int resultSetType,
			int resultSetConcurrency,
			int resultSetHoldability)
    throws SQLException
	{
		return new EmbedCallableStatement30(conn,
								stmt,
								resultSetType,
								resultSetConcurrency,
								resultSetHoldability);
	}
	public BrokeredConnection newBrokeredConnection(BrokeredConnectionControl control) {

		return new BrokeredConnection30(control);
	}
}
