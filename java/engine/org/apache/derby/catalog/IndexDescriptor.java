/*

   Licensed Materials - Property of IBM
   Cloudscape - Package org.apache.derby.catalog
   (C) Copyright IBM Corp. 1997, 2004. All Rights Reserved.
   US Government Users Restricted Rights - Use, duplication or
   disclosure restricted by GSA ADP Schedule Contract with IBM Corp.

 */

package org.apache.derby.catalog;

/**
 *	
 * This interface describes an index.
 * 
 * It is used in the column SYS.SYSCONGLOMERATES.DESCRIPTOR
 * and describes everything about an index except the index name and 
 * the table on which the index is defined.
 * That information is available 
 * in the columns NAME and TABLEID of the table SYS.SYSCONGLOMERATES.
 */
public interface IndexDescriptor
{
	/**
		IBM Copyright &copy notice.
	*/
	public static final String copyrightNotice = org.apache.derby.iapi.reference.Copyright.SHORT_1997_2004;
	/**
	 * Returns true if the index is unique.
	 */
	boolean			isUnique();

	/**
	 * Returns an array of column positions in the base table.  Each index
	 * column corresponds to a column position in the base table, except
	 * the column representing the location of the row in the base table.
	 * The returned array holds the column positions in the
	 * base table, so, if entry 2 is the number 4, the second
	 * column in the index is the fourth column in the table.
	 */
	public int[]	baseColumnPositions();

	/**
	 * Returns the position of a column within the key (1-based).
	 * 0 means that the column is not in the key.
	 */
	public Integer getKeyColumnPosition(Integer heapColumnPosition);

	/**
	 * Returns the position of a column within the key (1-based).
	 * 0 means that the column is not in the key.  Same as the above
	 * method, but it uses int instead of Integer.
	 */
	public int getKeyColumnPosition(int heapColumnPosition);

	/**
	 * Returns the number of ordered columns.  In the future, it will be
	 * possible to store non-ordered columns in an index.  These will be
	 * useful for covered queries.  The ordered columns will be at the
	 * beginning of the index row, and they will be followed by the
	 * non-ordered columns.
	 *
	 * For now, all columns in an index must be ordered.
	 */
	int				numberOfOrderedColumns();

	/**
	 * Returns the type of the index.  For now, we only support B-Trees,
	 * so the value "BTREE" is returned.
	 */
	String			indexType();

	/**
	 * Returns array of boolean telling asc/desc info for each index
	 * key column for convenience of using together with baseColumnPositions
	 * method.  Both methods return an array with subscript starting from 0.
	 */
	public boolean[]	isAscending();

	/**
	 * Returns true if the specified column is ascending in the index
	 * (1-based).
	 */
	boolean			isAscending(Integer keyColumnPosition);

	/**
	 * Returns true if the specified column is descending in the index
	 * (1-based).  In the current release, only ascending columns are
	 * supported.
	 */
	boolean			isDescending(Integer keyColumnPosition);

	/**
	 * set the baseColumnPositions field of the index descriptor.  This
	 * is for updating the field in operations such as "alter table drop
	 * column" where baseColumnPositions is changed.
	 */
	public void     setBaseColumnPositions(int[] baseColumnPositions);

	/**
	 * set the isAscending field of the index descriptor.  This
	 * is for updating the field in operations such as "alter table drop
	 * column" where isAscending is changed.
	 */
	public void     setIsAscending(boolean[] isAscending);

	/**
	 * set the numberOfOrderedColumns field of the index descriptor.  This
	 * is for updating the field in operations such as "alter table drop
	 * column" where numberOfOrderedColumns is changed.
	 */
	public void     setNumberOfOrderedColumns(int numberOfOrderedColumns);
}
