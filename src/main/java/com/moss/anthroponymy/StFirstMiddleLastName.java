/**
 * Copyright (C) 2013, Moss Computing Inc.
 *
 * This file is part of anthroponymy.
 *
 * anthroponymy is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * anthroponymy is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with anthroponymy; see the file COPYING.  If not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 *
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library.  Thus, the terms and
 * conditions of the GNU General Public License cover the whole
 * combination.
 *
 * As a special exception, the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent
 * modules, and to copy and distribute the resulting executable under
 * terms of your choice, provided that you also meet, for each linked
 * independent module, the terms and conditions of the license of that
 * module.  An independent module is a module which is not derived from
 * or based on this library.  If you modify this library, you may extend
 * this exception to your version of the library, but you are not
 * obligated to do so.  If you do not wish to do so, delete this
 * exception statement from your version.
 */
package com.moss.anthroponymy;

@SuppressWarnings("serial")
public class StFirstMiddleLastName extends StFullHumanName{
	
	public static class FirstName extends StHumanNamePart {
		public FirstName(String text) {
			super(text, false);
		}
	}
	public static class LastName extends StHumanNamePart {
		public LastName(String text) {
			super(text, false);
		}
	}
	public static class MiddleInitial extends StHumanNamePart {
		public MiddleInitial(String text) {
			super(text, true);
			if(text.length()>1) 
				throw new IllegalArgumentException("Middle Initials Must be One Character.");
		}
	}
	
	private FirstName firstname;
	private MiddleInitial middleInitial;
	private LastName lastname;
	
	private StSalutation salutation;
	
	public StFirstMiddleLastName() {}
	
	public StFirstMiddleLastName(StFirstMiddleLastName name){
		firstname = name.firstname;
		middleInitial = name.middleInitial;
		lastname = name.lastname;
	}
	
	public StFirstMiddleLastName(String firstname, String middleInitial, String lastname) {
		this.firstname = new FirstName(firstname);
		if(middleInitial!=null)
			this.middleInitial = new MiddleInitial(middleInitial);
		this.lastname = new LastName(lastname);
	}
	
	public StFirstMiddleLastName(String firstname, String lastname){
		this.firstname = new FirstName(firstname);
		this.lastname = new LastName(lastname);
	}
	
	@Override
	public StHumanNamePart[] parts() {
		if(middleInitial==null){
			return new StHumanNamePart[]{firstname, lastname};
		}else{
			return new StHumanNamePart[]{firstname, middleInitial, lastname};
		}
	}
	
	/**
	 * @return Returns the firstname.
	 */
	public String getFirstname() {
		return firstname.toString();
	}
	
	public void setFirstname(String firstname) {
		this.firstname = new FirstName(firstname);
	}
	
	public String getLastname() {
		return lastname.toString();
	}
	
	public void setLastname(String lastname) {
		this.lastname = new LastName(lastname);
	}
	
	public String getMiddleInitial() {
		return middleInitial!=null?middleInitial.toString():null;
	}
	
	public void setMiddleInitial(String middleInitial) {
		if(middleInitial==null){
			this.middleInitial = null;
		}else{
			this.middleInitial = new MiddleInitial(middleInitial);
		}
	}
	
	public String toString(){
		String fullname = "";
		if(firstname!=null) fullname += firstname;	/**
		 * @param middleInitial The middleInitial to set.
		 */

		if(middleInitial!=null) fullname +=" " + middleInitial;
		if(lastname != null) fullname += " " + lastname;
		return fullname;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj==null || !(obj instanceof StFirstMiddleLastName)) return false;
		StFirstMiddleLastName other = (StFirstMiddleLastName)obj;
		
		if(firstname!=null && !firstname.equals(other.firstname)) return false;
		if(firstname==null && other.firstname!=null) return false;
		
		if(lastname!=null && !lastname.equals(other.lastname)) return false;
		if(lastname==null && other.lastname!=null) return false;
		
		if(middleInitial!=null && !middleInitial.equals(other.middleInitial)) return false;
		if(middleInitial==null && other.middleInitial!=null) return false;
		
		return true;
	}

	public StSalutation getSalutation() {
		return salutation;
	}

	public void setSalutation(StSalutation salutation) {
		this.salutation = salutation;
	}
}
 