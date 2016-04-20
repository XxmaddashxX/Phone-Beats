package net.driftingcolossus.phonebeats.framework;

public enum LanguageSet {

		ENG_UK, ENG_US;


		@Override
		public String toString() {
			switch(this){
			case ENG_UK:
				return "en_UK";
			case ENG_US:
				return "en_US";
			default:
				return "N/A";
			
			}
		}
		
		
	
}
