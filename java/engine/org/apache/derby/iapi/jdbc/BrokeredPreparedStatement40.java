/*
 
   Derby - Class org.apache.derby.iapi.jdbc.BrokeredPreparedStatement40
 
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

package org.apache.derby.iapi.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.sql.NClob;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import org.apache.derby.impl.jdbc.Util;
import org.apache.derby.iapi.reference.SQLState;

public class BrokeredPreparedStatement40 extends BrokeredPreparedStatement30{
    
    public BrokeredPreparedStatement40(BrokeredStatementControl control, int jdbcLevel, String sql, Object generatedKeys) throws SQLException {
        super(control, jdbcLevel, sql,generatedKeys);
    }
    
    public void setRowId(int parameterIndex, RowId x) throws SQLException{
        throw Util.notImplemented();
    }
    
    public void setNString(int index, String value) throws SQLException{
        throw Util.notImplemented();
    }
    
    public void setNCharacterStream(int index, Reader value, long length) throws SQLException{
        throw Util.notImplemented();
    }
    
    public void setNClob(int index, NClob value) throws SQLException{
        throw Util.notImplemented();
    }
    
    public void setClob(int parameterIndex, Reader reader, long length)
    throws SQLException{
        throw Util.notImplemented();
    }
    
    public void setBlob(int parameterIndex, InputStream inputStream, long length)
    throws SQLException{
        throw Util.notImplemented();
    }
    public void setNClob(int parameterIndex, Reader reader, long length)
    throws SQLException{
        throw Util.notImplemented();
    }
    
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException{
        throw Util.notImplemented();
    }
    
    public void setPoolable(boolean poolable)
    throws SQLException{
        throw Util.notImplemented();
    }
    
    public boolean isPoolable()
    throws SQLException{
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
        try {
            return interfaces.cast(this);
        } catch (ClassCastException cce) {
            throw Util.generateCsSQLException(SQLState.UNABLE_TO_UNWRAP,
                    interfaces);
        }
    }
    
}
