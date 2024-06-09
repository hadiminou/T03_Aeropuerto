package fp.aeropuerto;

import utiles.Checkers;

public record Persona(String dni, String nombre,String apellidos)
							implements Comparable<Persona>{
	
	public Persona{
		Checkers.checkNoNull(dni, apellidos, nombre);
	}
	public Persona(String apellidos, String nombre) {
		this("",nombre,apellidos);
	}
		
	public boolean equals(Object o) {
		boolean res=false;
		if (o instanceof Persona) {
			Persona p=(Persona)o;
			res=this.dni().equals(p.dni()) &&
				this.nombre().equals(p.nombre()) &&
				this.apellidos().equals(p.apellidos());
		}
		return res;
	}
	public int hashCode() {
		return 11*this.dni().hashCode()+
			   13*this.apellidos().hashCode()+
			   17*this.nombre().hashCode();
	}
	
	public int compareTo(Persona p) {
		int res=this.dni().compareTo(p.dni());
		if (res==0) {
			res=this.apellidos().compareTo(p.apellidos());
			if(res==0) {
				res=this.nombre().compareTo(p.nombre());
			}
		}
		return res;
	}

}
