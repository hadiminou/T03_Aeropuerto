package fp.aeropuerto;


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import utiles.Checkers;

public record Aeropuerto(String nombre, String localidad, List<Vuelo> vuelos)
			implements Comparable<Aeropuerto>{
	
	public Aeropuerto {
		Checkers.checkNoNull(nombre,localidad, vuelos);
		vuelos = List.copyOf(vuelos);
	}

	public Aeropuerto(String nombre, String localidad, Stream<Vuelo> vuelos) {
		this(nombre, localidad, vuelos.toList());
	}

	public Integer numeroDeVuelos() {
		return this.vuelos.size();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Aeropuerto))
			return false;
		Aeropuerto other = (Aeropuerto) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	public String toString() {
		return  nombre + "-" + localidad +"(" + numeroDeVuelos() + ")";
	}

	@Override
	public int compareTo(Aeropuerto a) {
		int res = this.nombre().compareTo(a.nombre());
		return res;
	}
	
	public Boolean existeVueloADestino(String destino) {
		Boolean res = false;
		for(Vuelo v : this.vuelos()) {
			if(v.destino().toUpperCase().equals(destino.toUpperCase())) {
				res = true;
				break;
			}
		}
		return res;
	}
	
	public Boolean todosLosVueloCuestanMenosQue(Double precio) {
		Boolean res = true;
		for(Vuelo v : this.vuelos()) {
			if (v.precio() > precio) {
				res = false;
				break;
			}
		}
		return res;
	}
	
	public Vuelo vueloMasDuracion() {
		//return (Collections.max(vuelos, Comparator.comparing(vuelo->vuelo.duracion())));
		Vuelo res = null;
		for(Vuelo v: this.vuelos()) {
			if (res == null || v.duracion().compareTo(res.duracion())>0) {
				res = v;
			}
		}
		return res;
	}
	
	public Map<String, Integer> cuentaVuelosPorDestino (){
		Map<String, Integer> res = new HashMap<String, Integer>();
		for (Vuelo v: this.vuelos()) {
			if(!res.containsKey(v.destino())) {
				res.put(v.destino(), 1);
			}
			else {
				Integer contador = res.get(v.destino());
				contador++;
				res.put(v.destino(), contador);
			}
		}
		return res;
	}
	
	public Map<Compania, Set<String>> distintosDestinosPorCompania (){
		Map<Compania, Set<String>> res = new HashMap<Compania, Set<String>>();
		for (Vuelo v: this.vuelos()) {
			if(!res.containsKey(v.compania())) {
				Set<String> aux = new HashSet<String>();
				aux.add(v.destino());
				res.put(v.compania(),aux);
			}
			else {
				Set<String> aux = res.get(v.compania());
				aux.add(v.destino());
				res.put(v.compania(), aux);
			}
		}
		return res;
	}
	
	public Map<Compania, Set<String>> distintosDestinosVuelosCompletosPorCompania (){
		Map<Compania, Set<String>> res = new HashMap<Compania, Set<String>>();
		for (Vuelo v : this.vuelos()) {
			if (v.vueloCompleto()) {
				if(!res.containsKey(v.compania())) {
					Set<String> aux = new HashSet<String>();
					aux.add(v.destino());
					res.put(v.compania(), aux);
				}
				else {
					Set<String> aux = res.get(v.compania());
					aux.add(v.destino());
					res.put(v.compania(), aux);
				}
			}
		}
		return res;
	}

	public List<Vuelo> vuelosPorOrdenNatural() {
		List<Vuelo> aux= new ArrayList<Vuelo>(this.vuelos());
		Collections.sort(aux);
		return aux;
	}
	
	public Vuelo maximoVueloPorOrdenNatural() {
		return Collections.max(this.vuelos());
	}
	
	public List<Vuelo> vuelosPorInversoAlOrdenNatural(){
		Comparator <Vuelo> cmp = Comparator.reverseOrder();
		List<Vuelo> aux = new ArrayList<Vuelo>(this.vuelos());
		Collections.sort(aux,cmp);
		return aux;
	}
	
	public List<Vuelo> vuelosPorPrecioYHoraSalida(){
		Comparator<Vuelo> cmp = Comparator.comparing(Vuelo::precio).
				thenComparing(v->v.fechaHoraSalida().toLocalTime());
		List<Vuelo> aux = new ArrayList<Vuelo>(this.vuelos());
		Collections.sort(aux, cmp);
		return aux;
	}
	
	public List<Vuelo> vuelosPorDuracionYMayorNroPasajeros(){
		Comparator<Vuelo> cmp1 = Comparator.comparing(Vuelo::duracion);
		Comparator<Vuelo> cmp2 = Comparator.comparing(Vuelo::numeroPasajeros).reversed();
		Comparator<Vuelo> cmp3 = cmp1.thenComparing(cmp2);
		List<Vuelo> aux = new ArrayList<Vuelo>(this.vuelos());
		Collections.sort(aux, cmp3);
		return aux;
	}
	
	public List<Persona> pasajerosDePrimerVueloPorNombreYDni(){
		Comparator<Persona> cmp = Comparator.comparing(Persona::nombre).thenComparing(Persona::dni);
		List<Persona> aux = new ArrayList<Persona>(this.vuelos().get(0).pasajeros());
		Collections.sort(aux, cmp);
		return aux;
	}
	
	public SortedSet<String> diferentesNombresDePasajerosPorOrdenAlfabeticoInverso() {
		Comparator<String> cmp = Comparator.reverseOrder();
		SortedSet<String> aux = new TreeSet<String>(cmp);
		for(Vuelo v: this.vuelos()) {
			for(Persona p : v.pasajeros()) {
				aux.add(p.nombre());
			}
		}
		return aux;
	}
	
	public List<Persona> pasajerosDeTodosLosVuelosPorApellidosYNombre(){
		Comparator<Persona> cmp1 = Comparator.comparing(p->p.apellidos());
		Comparator<Persona> cmp2 = Comparator.comparing(p->p.nombre());
		List<Persona> aux = new ArrayList<Persona>();
		for(Vuelo v : this.vuelos()) {
			aux.addAll(v.pasajeros());
		}
		Collections.sort(aux, cmp1.thenComparing(cmp2));
		return aux;
	}
	
	public List<Persona> pasajerosSinRepetirDeTodosLosVuelosPorApellidosYNombre(){
		Comparator<Persona> cmp1 = Comparator.comparing(p->p.apellidos());
		Comparator<Persona> cmp2 = Comparator.comparing(p->p.nombre());
		SortedSet<Persona> aux = new TreeSet<Persona>(cmp1.thenComparing(cmp2));
		for(Vuelo v : this.vuelos()) {
			aux.addAll(v.pasajeros());
		}
		
		return new ArrayList<Persona>(aux);
	}
	
	public Integer numeroVuelosADestino(String destino) {
		return (int)this.vuelos().stream()
				.filter(v->v.destino().
				equals(destino)).
				count();
	}
	
	public Integer numeroPasajerosADestino(String destino) {
		return (int)this.vuelos().stream().
				filter(v->v.destino().equals(destino)).
				mapToInt(Vuelo::numeroPasajeros).
				sum();
	}
	
	public Vuelo vueloMenorRecaudacionVuelosCompletos() {
		Comparator<Vuelo> cmp = Comparator.comparing(v->v.precio()*v.numeroPasajeros());
		return this.vuelos().stream().
				filter(v->v.vueloCompleto()).
				min(cmp).
				get();
	}
	
	public String codigoDeAlgunVueloADestinoConPlazasLibres(String destino) {
		Vuelo vuelo = this.vuelos().stream().
				filter(v->v.destino().equals(destino) && !v.vueloCompleto()).
				findAny().orElse(null);
		String res = null;
		if(vuelo != null) {
			res = vuelo.codigo();
		}
		return res;
	}
	
	public Boolean existeVueloPrecioMenorQue(Double precio) {
		return this.vuelos().stream().anyMatch(v->v.precio()<precio);
	}
	
	public Double promedioPreciosVuelosCompletos() {
		return this.vuelos().stream().
				filter(v->v.vueloCompleto()).
				mapToDouble(v->v.precio()).
				average().
				orElse(0);
	}
	
	public Double sumaPreciosDistintosVuelosCompletos() {
		return this.vuelos().stream().
				filter(v->v.vueloCompleto()).map(Vuelo::precio).
				distinct().
				mapToDouble(p->p).   // mapToDouble(Vuelo::precio)
				sum();
	}
	
	public Integer contarDistintosPasajeros() {
		return (int)this.vuelos().stream().
				flatMap(v->v.pasajeros().stream()).
				map(Persona::dni).
				distinct().
				count();
	}
	
	public Set<String> distintosDNIPasajeroDeCompania(Compania compania) {
		return this.vuelos().stream().
				filter(v->v.compania().equals(compania)).
				flatMap(v->v.pasajeros().stream()).
				map(Persona::dni).
				collect(Collectors.toSet());
	}
	
	public SortedSet<String>nombresOrdenadosDistintosPasajeroDeCompania(Compania compania) {
		return this.vuelos().stream().
				filter(v->v.compania().equals(compania)).
				flatMap(v->v.pasajeros().stream()).
				map(Persona::nombre).
				collect(Collectors.toCollection(()->new TreeSet<String>
				(Comparator.naturalOrder())));
	}
	
	public Map<String, List<Vuelo>> mapListaVuelosPorDestinos() {
		return this.vuelos().stream().
				collect(Collectors.groupingBy(Vuelo::destino));
	}
	
	public Map<LocalDate, Set<Vuelo>> mapSetVuelosPorFechaLlegada(){
		return this.vuelos().stream().
				collect(Collectors.groupingBy(v->v.fechaHoraLlegada().
						toLocalDate(), Collectors.toSet()));
	}
	
	public Map<LocalDate, SortedSet<Vuelo>> mapSetOrdenadoVuelosPorFechaSalida(){
		Comparator<Vuelo> cmp = Comparator.comparing(v->v.numeroPasajeros());
		return this.vuelos().stream().
				collect(Collectors.groupingBy(v->v.fechaHoraSalida().toLocalDate(),
						Collectors.toCollection(()->new TreeSet<Vuelo>(cmp))));
	}
	
	public Map<Compania, Integer>mapNumVuelosCompletosPorCompania() {
		return this.vuelos().stream().filter(v->v.vueloCompleto()).
				collect(Collectors.groupingBy(v->v.compania(),
				Collectors.collectingAndThen(Collectors.counting(),c->c.intValue())));
	} 
	
	public Map<String, SortedSet<Double>> mapPreciosOrdenadosPorDestino() {
		return this.vuelos().stream().collect(Collectors.groupingBy(v->v.destino(),
				Collectors.mapping(Vuelo::precio,
				Collectors.toCollection(()->new TreeSet<Double>()))));  // TreeSet::new tambien sirve
	} 

	/******************21*****************/
	
	public SortedMap<Compania, Double> mapPrecioMedioPorCompania() {
		return this.vuelos().stream().
				collect(Collectors.groupingBy(Vuelo::compania, TreeMap::new,
						Collectors.averagingDouble(Vuelo::precio)));
	}
	
	public Map<String, Double> mapPrecioVuelosConEscalasMasBaratoPorDestino() {
		Comparator <Vuelo> cmp = Comparator.comparing(Vuelo::precio);
		return this.vuelos().stream().filter(v->v.conEscalas())
				.collect(Collectors.groupingBy(Vuelo::destino, 
						Collectors.collectingAndThen(Collectors.minBy(cmp),
								m->m.get().precio())));
	}
	
	public SortedMap<LocalTime, Integer> mapSumaPlazasLibresPorHoraDeLlegada(Compania c) {
		return this.vuelos().stream().filter(v->v.compania().equals(c)).
				collect(Collectors.groupingBy(v->v.fechaHoraLlegada().toLocalTime(),
						()->new TreeMap<LocalTime, Integer>(Comparator.reverseOrder()),
						Collectors.summingInt(v->v.numeroPlazas()-v.numeroPasajeros())));
	}
	
	/********************22*******************/
	
	public String destinoMayorNumeroDeplazasLibres() {
		return this.vuelos().stream().
				collect(Collectors.groupingBy(Vuelo::destino, 
				Collectors.summingInt(v->v.numeroPlazas()-v.numeroPasajeros()))).
				entrySet().
				stream().
				max(Comparator.comparing(Entry::getValue)).
				get().
				getKey();
	}
	
	public List <Double> promediosDePasajerosPorFechasDeSalida(){
		return this.vuelos().stream().
				collect(Collectors.groupingBy(v->v.fechaHoraSalida().toLocalDate(), 
						Collectors.averagingInt(Vuelo::numeroPasajeros))).
				entrySet().
				stream().
				sorted(Comparator.comparing(Entry::getKey)).
				map(Entry::getValue).
				collect(Collectors.toList());
	}
	
	public List<Entry<LocalDate,Double>> promediosDePasajerosPorFechasDeSalida2(){
		return this.vuelos().stream().
				collect(Collectors.groupingBy(v->v.fechaHoraSalida().toLocalDate(), 
						Collectors.averagingInt(Vuelo::numeroPasajeros))).
				entrySet().
				stream().
				sorted(Comparator.comparing(Entry::getKey)).
				collect(Collectors.toList());
	}
	
	public SortedMap<Duration, SortedSet<String>>mapDestinosPorDuracion(){
		return this.vuelos().stream()
					.collect(Collectors.groupingBy(Vuelo::duracion, 
					()->new TreeMap<Duration, SortedSet<String>>(Comparator.reverseOrder()),
					Collectors.mapping(Vuelo::destino,
					Collectors.toCollection(()->new TreeSet<String>(Comparator.reverseOrder())))));
	} 
	
	public SortedMap<String, Double> mapPorcentajeVuelosPorDestino(Double precio) {
		double totalVuelos = this.vuelos().stream()
				.filter(v->v.precio()>precio).count();
		SortedMap<String, Double> res = null;
		if (totalVuelos>0) {
			res = this.vuelos().stream()
					.filter(v->v.precio()>precio)
					.collect(Collectors.groupingBy(Vuelo::destino,
							()->new TreeMap<String, Double>(),
							Collectors.collectingAndThen(Collectors.counting(), 
									c->100*c/totalVuelos)));
		}
		return res;
	}
	
	public Compania companiaConMayorSumaDePlazasLibres() {
		return this.vuelos().stream()
				.collect(Collectors.groupingBy(Vuelo::compania,
						Collectors.summingInt(v->v.numeroPlazas()-v.numeroPasajeros())))
				.entrySet().stream()
				.max(Comparator.comparing(Entry::getValue))
				.get().getKey();
	}
	
	public LocalDate segundoDiaConMenosVuelos() {
		Comparator <Entry<LocalDate, Long>> cmp = Comparator.comparing(Entry::getValue);
		return this.vuelos().stream()
				.collect(Collectors.groupingBy(v->v.fechaHoraSalida().toLocalDate(),
						Collectors.counting()))
				.entrySet().stream()
				.sorted(cmp)
				.collect(Collectors.toList())
				.get(1).getKey();
	}
	
	public SortedMap<LocalDate, Set<Double>> mapPreciosSuperioresPorFechas() {
		return this.vuelos().stream()
				.collect(Collectors.groupingBy(v->v.fechaHoraLlegada().toLocalDate(),
						()->new TreeMap<LocalDate, Set<Double>>(Comparator.reverseOrder()),
						Collectors.collectingAndThen(Collectors.mapping(v->v.precio(),
								Collectors.toList()), l-> buscaPrecios(l))));
	}
	
	private Set<Double> buscaPrecios(List<Double> l) {
		double precioMaximo = l.stream().max(Comparator.naturalOrder()).get();
		double precioMinimo = l.stream().min(Comparator.naturalOrder()).get();
		double precioMedio = (precioMaximo+precioMinimo)/2;
		return l.stream()
				.filter(v->v>=precioMedio)
				.collect(Collectors.toSet());
	}

	public String destinoConMayorDiferenciaDePrecio() {
		//Devuelve el destino en el que la diferencia de precio entre el vuelo más caro y 
		//el más barato es mayor. 
		Comparator<Entry<String, Double>> cmp = Comparator.comparing(Entry::getValue);
		return this.vuelos().stream()
				.collect(Collectors.groupingBy(Vuelo::destino,
						Collectors.collectingAndThen(Collectors.toList(), l->buscaDiferencia(l))))
						.entrySet().stream()
						.max(Comparator.comparing(Entry::getValue)).get().getKey();
	}
	
	private Double buscaDiferencia(List<Vuelo> l) {
		double precioMaximo = l.stream().max(Comparator.comparing(Vuelo::precio)).get().precio();
		double precioMinimo = l.stream().min(Comparator.comparing(Vuelo::precio)).get().precio();
		return precioMaximo-precioMinimo;
	}

	public LocalDate fechaEnQueLLeganMasConDistintoNombrePasajerosConDestinos (Set<String> destinos) {
		/*
		 * dado un conjunto con nombres de destinos, se trata de encontrar la fecha de llegada en la que
		   hay más pasajeros con nombres distintos en los vuelos a alguno de los destinos dados. 
		   Realice el respectivo
		 */
		return this.vuelos().stream()
				.filter(v->destinos.contains(v.destino()))
				.collect(Collectors.groupingBy(v->v.fechaHoraLlegada().toLocalDate(),
						Collectors.collectingAndThen(
								Collectors.flatMapping(v->v.pasajeros().stream(), Collectors.toList()),
								l->l.stream().map(Persona::nombre).collect(Collectors.toSet()))))
				.entrySet().stream().max(Comparator.comparing(p->p.getValue().stream().count()))
				.get().getKey();
	}
	
	public SortedMap <String, Double> topNMediaPromediosPorDestino(Integer n){
		/*
		 * dado un entero n, devuelve un SortedMap que 
		   tiene como clave los destino en orden alfabético inverso y como valor la media de los “n”
		   precios más caros al destino de que se trate
		 */
		Comparator<Double> cmp = Comparator.reverseOrder();
		return this.vuelos().stream()
				.collect(Collectors.groupingBy(
						Vuelo::destino, ()->new TreeMap<String, Double>(Comparator.reverseOrder()),
						Collectors.collectingAndThen(
								Collectors.mapping(Vuelo::precio, Collectors.toList()),
								l->l.stream().sorted(Comparator.reverseOrder())
								.limit(n).mapToDouble(p->p).average().orElse(0))));
	}
	
	public Map<Persona, List<Vuelo>>vuelosPorPasajero() {
		/*
		 * [Hacerlo con bucles] devuelve un mapa que a cada pasajero le asocia una 
            lista con los vuelos en los que ha volado 
		 */
		Map<Persona, List<Vuelo>> res = new HashMap<Persona, List<Vuelo>>();
		for(Vuelo v: this.vuelos()) {
			for(Persona p : v.pasajeros()) {
				if(!res.containsKey(p)) {
					List<Vuelo> aux = new ArrayList<Vuelo>();
					aux.add(v);
					res.put(p, aux);
				}
				else {
					//res.get(p).add(v); otra opcion
					List<Vuelo> aux = res.get(p);
					aux.add(v);
					res.put(p, aux);
				}
			}
		}
		return res;
	}
	
	public Map<Persona, Duration>tiempoDeVuelosPorPasajero() {
		//devuelve un mapa que a cada pasajero le asocia la suma de duraciones de los vuelos realizados
		return this.vuelosPorPasajero().entrySet()
				.stream().collect(Collectors.toMap(Entry::getKey, 
						par->sumaDuraciones(par.getValue())));			
	}
	
	private Duration sumaDuraciones(List<Vuelo>l) {
		return Duration.ofMinutes(l.stream().mapToLong(v->v.duracion().toMinutes()).sum());
	}
	
	public String dniPasajeroMasTiempoDeVuelo() {
	//devuelve el pasajero que más tiempo ha volado
		return this.vuelosPorPasajero().entrySet().stream()
				.collect(Collectors.toMap(Entry::getKey, par->sumaDuraciones(par.getValue())))
				.entrySet().stream()
				.max(Comparator.comparing(Entry::getValue)).get().getKey().dni();
	}
	
}