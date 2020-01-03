package Ex1;

/** This enum presents the set of operations applicable on Ex1.function to compose a complex Ex1.function from two Ex1.functions.
 * E.g.,   Plus: plus(f1(x), f2(x)),  Times: mul(f1(x), f2(x)), Divid: div(f1(x), f2(x)), Max: max(f1(x), f2(x)), Min: min(f1(x), f2(x)), Comp: comp(f1(x), f2(x)) == f1(f2(x))
 * None representing no Ex1.Operation and Error representing an unknown (aka unsupported) Ex1.Operation.
 * @author boaz_benmoshe
 *
 */
public enum Operation {
	Plus, Times, Divid, Max, Min, Comp , None, Error;


	}


