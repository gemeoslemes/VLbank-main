package br.com.VLbank.model;

public enum Estados {
	 	VAZIO("VAZIO", "VAZIO", "VAZIO", Vazio.class),
	    MG("MG", "RG", "00.000.000", RgDeMg.class),
	    RJ("RJ", "RG", "00.000.000-0", RgDeSpRJ.class),
	    SP("SP", "RG", "00.000.000-0", RgDeSpRJ.class);

	    private final String estados;
	    private final String documento;
	    private final String mascara;
	    private final Class<?> group;

	    private Estados(String estados, String documento, String mascara, Class<?> group) {
			this.estados = estados;
			this.documento = documento;
			this.mascara = mascara;
			this.group = group;
		}

		public String getEstados() {
			return estados;
		}

		public String getDocumento() {
			return documento;
		}

		public String getMascara() {
			return mascara;
		}

		public Class<?> getGroup() {
			return group;
		}


}
