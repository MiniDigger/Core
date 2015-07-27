/*
Copyright Paul James Mutton, 2001-2004, http://www.jibble.org/

This file is part of JMegaHal.

This software is dual-licensed, allowing you to choose between the GNU
General Public License (GPL) and the www.jibble.org Commercial License.
Since the GPL may be too restrictive for use in a proprietary application,
a commercial license is also provided. Full license information can be
found at http://www.jibble.org/licenses/

$Author: pjm2 $
$Id: Quad.java,v 1.3 2004/02/01 13:24:06 pjm2 Exp $

 */

package org.jibble.jmegahal;

public class Quad implements java.io.Serializable {
	
	private static final long serialVersionUID = 5244814822007233095L;
	
	public Quad(final String s1, final String s2, final String s3, final String s4) {
		tokens = new String[] { s1, s2, s3, s4 };
	}
	
	public String getToken(final int index) {
		return tokens[index];
	}
	
	public void setCanStart(final boolean flag) {
		canStart = flag;
	}
	
	public void setCanEnd(final boolean flag) {
		canEnd = flag;
	}
	
	public boolean canStart() {
		return canStart;
	}
	
	public boolean canEnd() {
		return canEnd;
	}
	
	@Override
	public int hashCode() {
		return tokens[0].hashCode() + tokens[1].hashCode() + tokens[2].hashCode() + tokens[3].hashCode();
	}
	
	@Override
	public boolean equals(final Object o) {
		final Quad other = (Quad) o;
		return other.tokens[0].equals(tokens[0]) && other.tokens[1].equals(tokens[1]) && other.tokens[2].equals(tokens[2]) && other.tokens[3].equals(tokens[3]);
	}
	
	private final String[]	tokens;
	private boolean			canStart	= false;
	private boolean			canEnd	= false;
	
}
