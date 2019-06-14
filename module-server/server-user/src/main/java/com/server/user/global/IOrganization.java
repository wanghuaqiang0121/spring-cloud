package com.server.user.global;

public interface IOrganization {
	
	public enum OrganizationStatus{
		
		
		/**
		 *@Fields <font color="blue">ENABLE</font>
		 *@description 0 启用
		 */
		ENABLE(0),
		/**
		 *@Fields <font color="blue">DISABLE</font>
		 *@description 1 禁用
		 */
		DISABLE(1);
		
		private int value;

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
		private OrganizationStatus(int value) {
			this.value = value;
		}
	}
}
