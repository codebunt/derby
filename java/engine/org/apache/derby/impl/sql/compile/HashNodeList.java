/*

   Licensed Materials - Property of IBM
   Cloudscape - Package org.apache.derby.impl.sql.compile
   (C) Copyright IBM Corp. 1997, 2004. All Rights Reserved.
   US Government Users Restricted Rights - Use, duplication or
   disclosure restricted by GSA ADP Schedule Contract with IBM Corp.

 */

package	org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.error.StandardException;

import org.apache.derby.iapi.services.sanity.SanityManager;

import java.util.Hashtable;
import java.util.Enumeration;


/**
 * HashNodeList is the root class for all hashlists of query tree nodes.
 * It implements the QueryTree interface that is part of the Language
 * protocols
 *
 * @author Rick Hillegas
 */


public abstract class HashNodeList extends QueryTreeNode
{
	/**
		IBM Copyright &copy notice.
	*/
	public static final String copyrightNotice = org.apache.derby.iapi.reference.Copyright.SHORT_1997_2004;
	private	Hashtable		hashtable = new Hashtable();

	/////////////////////////////////////////////////////////////////
	//
	//	HASHTABLE FORWARDS
	//
	/////////////////////////////////////////////////////////////////

	/**
	  *	Add an element to this hash list.
	  *
	  *	@param	key		hash key for new value
	  *	@param	value	new item to add to list
	  *
	  */
	public	void	add( Object key, Object value )
	{
		hashtable.put( key, value );
	}


    /**
	  *	Returns the size of the list.
	  *
	  *	@return	size of the list
	  */
    public	int	size() { return hashtable.size(); }

	/**
	  *	Get an iterator to walk this hash list
	  *
	  *	@return	an Enumeration for walking this hash list
	  */
	public	Enumeration	elements()
	{
		return	hashtable.elements();
	}


	/**
	  *	Gets an element by key
	  *
	  *	@param	key		hash key to lookup
	  *
	  *	@return	the element associated with the hash key
	  *			null if no element with that key exists
	  *
	  */
	public	Object	get( Object key )
	{
		return	hashtable.get( key );
	}


	/////////////////////////////////////////////////////////////////
	//
	//	OBJECT SUPPORT
	//
	/////////////////////////////////////////////////////////////////

	/**
	 * Convert this object to a String.  See comments in QueryTreeNode.java
	 * for how this should be done for tree printing.
	 *
	 * @return	This object as a String
	 */

	public String toString()
	{
		if (SanityManager.DEBUG)
		{
			Enumeration		iterator;
			StringBuffer	buffer = new StringBuffer("");
			Object			item;

			for (iterator = elements(); iterator.hasMoreElements() == true; )
			{
				item = iterator.nextElement();
				buffer.append(item.toString());
				buffer.append("\n");
			}

			return buffer.toString();
		}
		else
		{
			return "";
		}
	}

	/////////////////////////////////////////////////////////////////
	//
	//	QUERY TREE NODE METHODS
	//
	/////////////////////////////////////////////////////////////////

	/**
	 * Get the optimizer's cost estimate for an optimized QueryTree.
	 * For non-optimizable statements (for example, CREATE TABLE),
	 * return null.  For optimizable statements, this method will be
	 * over-ridden in the statement's root node (DMLStatementNode in
	 * all cases we know about so far).
	 *
	 * @return	null
	 */
	// public CostEstimate	getCostEstimate()
	// {
		// return null;
	// }

	/**
	 * Returns whether or not this Statement requires a set/clear savepoint
	 * around its execution.  The following statement "types" do not require them:
	 *		Cursor	- unnecessary and won't work in a read only environment
	 *		Xact	- savepoint will get blown away underneath us during commit/rollback
	 *
	 * @return boolean	Whether or not this Statement requires a set/clear savepoint
	 */
	public boolean needsSavepoint()
	{
		if (SanityManager.DEBUG)
		{
			SanityManager.ASSERT(false,
				"needsSavepoint() not expected to be called.");
		}
		return false;
	}
}
