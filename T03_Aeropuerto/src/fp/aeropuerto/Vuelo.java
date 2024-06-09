package fp.aeropuerto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import utiles.Checkers;



//atributos = propiedades basicas
public record Vuelo(String codigo, String destino, LocalDateTime fechaHoraSalida, Duration duracion,
		Double velocidad, Double precio, Integer numeroPlazas, Boolean conEscalas,
		List<Persona> pasajeros) implements Comparable<Vuelo>{
		
	
	//propiedades derivadas:Integer numeroPasajeros,Boolean vueloCompleto,Double porcentajeOcupacion
	// LocalDateTime fechaHoraLLegada, Compania compania
	// son las propiedades que se puede crear con las propiedades basicas
	
	/*public Vuelo(String codigo,String destino,LocalDateTime fechaHoraSalida, Duration duracion, 
			Double velocidad, Double precio, Integer numeroPlazas,Boolean conEscalas,
			List<Persona> pasajeros,Integer numeroPasajeros, Boolean vueloCompleto, 
			Double porcentajeOcupacion, LocalDateTime fechaHoraLLegada,Compania compania) {
		this(codigo, destino, fechaHoraSalida, duracion, velocidad, precio, numeroPlazas, conEscalas,
			 pasajeros, numeroPasajeros, vueloCompleto,porcentajeOcupacion, fechaHoraLLegada, compania);
	} 
	no hace falta crear este constructor porque lo nos regala el record  */
	
	public Vuelo(String codigo,String destino, LocalDateTime fechaHoraSalida,Duration duracion,
			Double velocidad,Double precio,Integer numeroPlazas,Boolean conEscalas) {
		this(codigo, destino, fechaHoraSalida, duracion, velocidad, precio, numeroPlazas,
				conEscalas,new ArrayList<Persona>());
	}
	
	public Vuelo {
		pasajeros=List.copyOf(pasajeros);  // Esta sentencia copia la lista pasajeros sobre ella misma,
												//pero haciendo inmutable la lista.
		Checkers.checkNoNull(codigo, destino, fechaHoraSalida, duracion, velocidad, precio, numeroPlazas,
				conEscalas, pasajeros);
			Checkers.check("El formato del codigo debe ser aaa-nnn", codigo.length()==7 &&
					Character.isAlphabetic(codigo.charAt(0)) && 
					Character.isAlphabetic(codigo.charAt(1)) &&
					Character.isAlphabetic(codigo.charAt(2)) &&
					codigo.charAt(3)=='-' && 
					Character.isDigit(codigo.charAt(4)) &&
					Character.isDigit(codigo.charAt(5)) &&
					Character.isDigit(codigo.charAt(6)));
			Checkers.check("El preecio de billete debe ser >= 0", precio>=0);
			Checkers.check("La velocidad debe ser >= 0", velocidad >= 0);
			Checkers.check("La duracion debe ser > 0", duracion.compareTo(Duration.ZERO) > 0);
			Checkers.check("La numero plazas debe ser >= 0", numeroPlazas >= 0);
			Checkers.check("La numero de pasajeros debe ser <= numero de plazas",
					pasajeros.size() <= numeroPlazas);
	}
	
	public Integer numeroPasajeros() {
		return this.pasajeros.size();
	}
	
	public Boolean vueloCompleto() {
		return numeroPasajeros() == this.numeroPlazas; 
		//ponemos parantesis porque numeroPasajeros() ya es un metodo
	}
	
	public Double porcentajeOcupacion() {
		return (this.pasajeros.size()/numeroPlazas)*100.0; //100, (double)  casting
	}
	
	public LocalDateTime fechaHoraLlegada() {
		return this.fechaHoraSalida.plus(duracion);
	}
	
	public Compania compania() {
		return Compania.valueOf(codigo.substring(0,3));
	}

	public String codigo() {
		return codigo;
	}

	public String destino() {
		return destino;
	}

	public LocalDateTime fechaHoraSalida() {
		return fechaHoraSalida;
	}

	public Duration duracion() {
		return duracion;
	}

	public Double velocidad() {
		return velocidad;
	}

	public Double precio() {
		return precio;
	}

	public Integer numeroPlazas() {
		return numeroPlazas;
	}

	public Boolean conEscalas() {
		return conEscalas;
	}

	public List<Persona> pasajeros() {
		return pasajeros;
	}
	
	public String toString() {
		return "Vuelo [codigo=" + codigo + ", destino=" + destino +
				", fechaHoraSalida=" + fechaHoraSalida  + ", fechaHoraLlegada=" + fechaHoraLlegada() +
				", Nro.pasajeros=" + pasajeros.size() + "]";
	} 

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((destino == null) ? 0 : destino.hashCode());
		result = prime * result + ((fechaHoraSalida.toLocalDate() == null) ? 0 :
			fechaHoraSalida.toLocalDate().hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Vuelo))
			return false;
		Vuelo other = (Vuelo) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (destino == null) {
			if (other.destino != null)
				return false;
		} else if (!destino.equals(other.destino))
			return false;
		if (fechaHoraSalida == null) {
			if (other.fechaHoraSalida != null)
				return false;
		} else if (!fechaHoraSalida.toLocalDate().equals(other.fechaHoraSalida.toLocalDate()))
			return false;
		return true;
	}

	public int compareTo(Vuelo v) {
		int res = this.codigo().compareTo(v.codigo());
		if (res == 0) {
			res = this.destino().compareTo(v.destino());
			if (res == 0) {
				res = this.fechaHoraSalida().toLocalDate().compareTo(v.fechaHoraSalida().toLocalDate());
			}
		}
		return res;
	}
}