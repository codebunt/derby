/*
 
   Derby - Class org.apache.derby.impl.jdbc.EmbedResultSet40
 
   Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
      http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 
 */

package org.apache.derby.impl.jdbc;

import java.sql.NClob;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import org.apache.derby.iapi.sql.ResultSet;
import java.sql.Statement;

import org.apache.derby.iapi.reference.SQLState;

/**
 * Implementation of JDBC 4 specific ResultSet methods.
 */
public class EmbedResultSet40 extends org.apache.derby.impl.jdbc.EmbedResultSet20{
    
    /** Creates a new instance of EmbedResultSet40 */
    public EmbedResultSet40(org.apache.derby.impl.jdbc.EmbedConnection conn,
        ResultSet resultsToWrap,
        boolean forMetaData,
        org.apache.derby.impl.jdbc.EmbedStatement stmt,
        boolean isAtomic)
        throws SQLException {
        
        super(conn, resultsToWrap, forMetaData, stmt, isAtomic);
    }
    
    public RowId getRowId(int columnIndex) throws SQLException {
        throw Util.notImplemented();
    }
    
    
    public RowId getRowId(String columnName) throws SQLException {
        throw Util.notImplemented();
    }
    
    public void updateRowId(int columnIndex, RowId x) throws SQLException {
        throw Util.notImplemented();
    }
    
    public void updateRowId(String columnName, RowId x) throws SQLException {
        throw Util.notImplemented();
    }
    
    public void updateNString(int columnIndex, String nString) throws SQLException {
        throw Util.notImplemented();
    }
    
    public void updateNString(String columnName, String nString) throws SQLException {
        throw Util.notImplemented();
    }
    
    public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
        throw Util.notImplemented();
    }
    
    public void updateNClob(String columnName, NClob nClob) throws SQLException {
        throw Util.notImplemented();
    }
    
    public NClob getNClob(int i) throws SQLException {
        throw Util.notImplemented();
    }
    
    public NClob getNClob(String colName) throws SQLException {
        throw Util.notImplemented();
    }
    
    public SQLXML getSQLXML(int columnIndex) throws SQLException {
        throw Util.notImplemented();
    }
    
    public SQLXML getSQLXML(String colName) throws SQLException {
        throw Util.notImplemented();
    }
    
    public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
        throw Util.notImplemented();
    }
    
    public void updateSQLXML(String columnName, SQLXML xmlObject) throws SQLException {
        throw Util.notImplemented();
    }
    
    /**
     * Returns false unless <code>interfaces</code> is implemented 
     * 
     * @param  interfaces             a Class defining an interface.
     * @return true                   if this implements the interface or 
     *                                directly or indirectly wraps an object 
     *                                that does.
     * @throws java.sql.SQLException  if an error occurs while determining 
     *                                whether this is a wrapper for an object 
     *                                with the given interface.
     */
    public boolean isWrapperFor(Class<?> interfaces) throws SQLException {
        return interfaces.isInstance(this);
    }
    
    /**
     * Returns <code>this</code> if this class implements the interface
     *
     * @param  interfaces a Class defining an interface
     * @return an object that implements the interface
     * @throws java.sql.SQLExption if no object if found that implements the 
     * interface
     */
    public <T> T unwrap(java.lang.Class<T> interfaces) 
                            throws SQLException{
        //Derby does not implement non-standard methods on 
        //JDBC objects
        //hence return this if this class implements the interface 
        //or throw an SQLException
        try {
            return interfaces.cast(this);
        } catch (ClassCastException cce) {
            throw newSQLException(SQLState.UNABLE_TO_UNWRAP,interfaces);
        }
    }
    
}
