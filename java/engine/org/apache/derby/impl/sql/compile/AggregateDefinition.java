/*

   Licensed Materials - Property of IBM
   Cloudscape - Package org.apache.derby.impl.sql.compile
   (C) Copyright IBM Corp. 1999, 2004. All Rights Reserved.
   US Government Users Restricted Rights - Use, duplication or
   disclosure restricted by GSA ADP Schedule Contract with IBM Corp.

 */

package	org.apache.derby.impl.sql.compile;

import java.lang.StringBuffer;
import org.apache.derby.catalog.TypeDescriptor;
import java.sql.SQLException;

/**
 * An AggregateDefinition defines an aggregate.
 * 
 * It is used
 * by Cloudscape during query compilation to determine what 
 * Aggregator is used to aggregate a particular data type 
 * and what datatype the Aggregator will emit.  A single 
 * AggregateDefinition may map to one or more Aggregators 
 * depending on the input type.  For example, a user defined
 * STDEV aggregate may use one aggregator implementation for the
 * INTEGER type and another for a user defined type that implements 
 * a point.  In this case, both the aggregators would have a 
 * single AggregateDefinition that would chose the appropriate
 * aggregator based on the input type.  On the other hand, if
 * only a single aggregator is needed to aggregate over all
 * of the input types (e.g. COUNT()), then it may be convenient
 * to implement both the AggregateDefinition and the Aggregator
 * interfaces by the same class.
 * <P>
 * <I> IBM Corp. reserves the right to change, rename, or
 * remove this interface at any time. </I>
 *
 * @see com.ibm.db2j.aggregates.Aggregator
 * @see org.apache.derby.catalog.TypeDescriptor
 * @see com.ibm.db2j.types.TypeFactory
 * @see org.apache.derby.iapi.db.Factory#getTypeFactory
 */
interface AggregateDefinition
{
	/**
		IBM Copyright &copy notice.
	*/
	public static final String copyrightNotice = org.apache.derby.iapi.reference.Copyright.SHORT_1999_2004;
	/**
	 * Get the aggregator that performs the aggregation on the
	 * input datatype at execution time.  If the input type can be handled, 
	 * return a type descriptor with the resultant type information and
	 * fill in the string buffer with the name of the class that
	 * is used to perform the aggregation over the input type.
	 * If the aggregate cannot be performed on this type, then
	 * a null should be returned.
	 * <p>
	 * The aggregator class must implement a zero argument 
	 * constructor.  The aggregator class can be the same class
	 * as the AggregateDefinition if it implements both interfaces.
	 * <p>
	 * The result datatype may be the same as the input datatype 
	 * or a different datatype.  To create your own type descriptor
	 * to return to this method, see <i>com.ibm.db2j.types.TypeFactory</i>.
	 *
	 * @param inputType	the input type descriptor
	 * @param aggregatorClassName	output parameter, filled in
	 *		with the class name that implements <i>com.ibm.db2j.aggregates.Aggregator</i>
	 *
	 * @return the output type descriptor (which may or may not
	 *		be the same as the input type -- it is ok to simply
	 *		return the input type).  Null is returned
	 *		if the aggregate cannot process the input type.
	 *		Note that the output type may be a type that maps
	 * 		directly to a standard SQL (e.g. <i>java.lang.Integer</i>)
	 *		or any other java type (e.g. <i>java.sql.ResultSet</i>,
	 *		<i>java.util.Vector</i>, <i>java.util.TimeZone</i> or whatever).
	 *		To construct a type descriptor see <i>com.ibm.db2j.types.TypeFactory</i>.
	 *
	 * @see org.apache.derby.catalog.TypeDescriptor
	 * @see com.ibm.db2j.types.TypeFactory
	 * @see com.ibm.db2j.aggregates.Aggregator
	 *
	 * @exception SQLException Thrown on error.
	 */
	public	TypeDescriptor getAggregator(TypeDescriptor inputType,
							StringBuffer aggregatorClassName)
							throws SQLException;
}
