/*

   Licensed Materials - Property of IBM
   Cloudscape - Package org.apache.derby.impl.sql.catalog
   (C) Copyright IBM Corp. 1998, 2004. All Rights Reserved.
   US Government Users Restricted Rights - Use, duplication or
   disclosure restricted by GSA ADP Schedule Contract with IBM Corp.

 */

package org.apache.derby.impl.sql.catalog;

import org.apache.derby.iapi.services.sanity.SanityManager;

import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;

import org.apache.derby.iapi.types.DataValueDescriptor;

import org.apache.derby.iapi.types.BooleanDataValue;

import org.apache.derby.iapi.sql.execute.ExecutionFactory;

import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.types.DataValueFactory;

import org.apache.derby.iapi.services.monitor.Monitor;

import org.apache.derby.iapi.sql.execute.TupleFilter;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.error.StandardException;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.uuid.UUIDFactory;


/**
* A Filter to qualify tuples coming from a scan of SYSDEPENDS.
* Tuples qualify if they have the right providerID.
*
* @author Rick
*/
public class DropDependencyFilter implements TupleFilter
{
	/**
		IBM Copyright &copy notice.
	*/
	public static final String copyrightNotice = org.apache.derby.iapi.reference.Copyright.SHORT_1998_2004;
	//////////////////////////////////////////////////////////////////////////////////////
	//
	//	CONSTANTS
	//
	//////////////////////////////////////////////////////////////////////////////////////


	//////////////////////////////////////////////////////////////////////////////////////
	//
	//	STATE
	//
	//////////////////////////////////////////////////////////////////////////////////////

	UUID				providerID;

	UUIDFactory			uuidFactory = null;
	DataValueFactory	dataValueFactory = null;

	BooleanDataValue	trueValue;
	BooleanDataValue	falseValue;

	//////////////////////////////////////////////////////////////////////////////////////
	//
	//	CONSTRUCTORS
	//
	//////////////////////////////////////////////////////////////////////////////////////

	/**
	  *	Construct a TupleFilter to qualify SYSDEPENDS rows with the
	  * designated providerID.
	  *
	  *	@param	providerID	UUID of provider. Tuples with this ID qualify.
	  */
	public	DropDependencyFilter( UUID providerID )
	{
		this.providerID = providerID;
	}


	//////////////////////////////////////////////////////////////////////////////////////
	//
	//	TupleFilter BEHAVIOR
	//
	//////////////////////////////////////////////////////////////////////////////////////

	/**
	  *	Initialize a Filter with a vector of parameters. This is a NOP.
	  * We initialize this filter at Constructor time.
	  *
	  *	@param	parameters	An ExecRow of parameter values
	  *
	  * @exception StandardException		Thrown on error
	  */
    public	void	init( ExecRow parameters )
		throws StandardException
	{}

	/**
	  *	Pump a SYSDEPENDS row through the Filter. If the providerID of the
	  * row matches our providerID, we return true. Otherwise we return false.
	  *
	  *	@param	row		SYSDEPENDS row
	  *
	  *	@return	True if the row has our providerID. False otherwise.
	  *
	  * @exception StandardException		Thrown on error
	  */
    public	BooleanDataValue	execute( ExecRow currentRow )
		throws StandardException
	{
		/* 3rd column is PROVIDERID (UUID - char(36)) */
		DataValueDescriptor	col = currentRow.getColumn(SYSDEPENDSRowFactory.SYSDEPENDS_PROVIDERID);
		String	providerIDstring = col.getString();
		UUID	providerUUID = getUUIDFactory().recreateUUID(providerIDstring);

		if ( providerID.equals( providerUUID ) ) { return getTrueValue(); }
		else { return getFalseValue(); }
	}


	//////////////////////////////////////////////////////////////////////////////////////
	//
	//	MINIONS
	//
	//////////////////////////////////////////////////////////////////////////////////////


	/**
	  *	Get the UUID factory
	  *
	  *	@return	the UUID factory
	  *
	  * @exception   StandardException thrown on failure
	  */
	private	UUIDFactory	getUUIDFactory()
		throws StandardException
	{
		if ( uuidFactory == null )
		{
			uuidFactory = Monitor.getMonitor().getUUIDFactory();
		}
		return	uuidFactory;
	}

	/**
	  *	Gets the DataValueFactory for this connection.
	  *
	  *	@return	the data value factory for this connection
	  */
	private DataValueFactory	getDataValueFactory()
	{
		if ( dataValueFactory == null )
		{
			LanguageConnectionContext	lcc = (LanguageConnectionContext) 
					                          ContextService.getContext
							                  (LanguageConnectionContext.CONTEXT_ID);

			dataValueFactory = lcc.getDataValueFactory();
		}

		return	dataValueFactory;
	}

	/**
	  *	Gets a BooleanDataValue representing TRUE.
	  *
	  *	@return	a TRUE value
	  * @exception StandardException		Thrown on error
	  */
	private BooleanDataValue	getTrueValue()
		throws StandardException
	{
		if ( trueValue == null )
		{
			trueValue = getDataValueFactory().getDataValue( true );
		}

		return	trueValue;
	}

	/**
	  *	Gets a BooleanDataValue representing FALSE
	  *
	  *	@return	a FALSE value
	  * @exception StandardException		Thrown on error
	  */
	private BooleanDataValue	getFalseValue()
		throws StandardException
	{
		if ( falseValue == null )
		{
			falseValue = getDataValueFactory().getDataValue( false );
		}

		return	falseValue;
	}

}
