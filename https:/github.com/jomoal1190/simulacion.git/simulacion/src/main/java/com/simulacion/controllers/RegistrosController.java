package com.simulacion.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simulacion.dao.RegistroDao;
import com.simulacion.methods.CalculoMinimosCuadrados;
import com.simulacion.methods.RespuestaBDanio;
import com.simulacion.methods.RespuestaMinimos;




@RestController
public class RegistrosController {
	//SE DEBE ENVIAR LA CANTIDAD DE ANIOS A PROYECTAR
	@Autowired RegistroDao registroDao;
	@RequestMapping(value = "/consultarInfo", method = RequestMethod.POST)
	 public List<RespuestaBDanio> consultarInfo(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		List<Map> respuesta = new ArrayList<Map>();
		respuesta = registroDao.findByParametrosAll();
		List<CalculoMinimosCuadrados> minimos = new ArrayList<CalculoMinimosCuadrados>();
		List<RespuestaBDanio> respuestabdProyeccion = new ArrayList<RespuestaBDanio>();
		for(Map mapa: respuesta)
		{
			Integer anioR = Integer.parseInt(mapa.get("anio").toString());
			Float total = Float.parseFloat(mapa.get("total").toString());
			RespuestaBDanio agregar = new RespuestaBDanio();
			agregar.setAnio(anioR.toString());
			agregar.setTotal(total);
			respuestabdProyeccion.add(agregar);
			CalculoMinimosCuadrados nuevoMinimo = new CalculoMinimosCuadrados();
			nuevoMinimo.setX(Float.parseFloat(anioR.toString()));
			nuevoMinimo.setY(total);
			nuevoMinimo.setXy(anioR*total);
			Float potencia = (float) (anioR*anioR);
			nuevoMinimo.setX2(potencia);
			minimos.add(nuevoMinimo);
			
		}
		Integer tamanioArray = minimos.size();
		Integer cantidadAnios= Integer.parseInt(request.getParameter("anios"));
		Integer valorAnio=minimos.get(tamanioArray-1).getX().intValue();
		RespuestaMinimos valores=valoresMinimos(minimos);
		
		Integer sumaAnio=0;
		for(int i=1; i<=cantidadAnios; i++)
		{
			sumaAnio=valorAnio+i;
			RespuestaBDanio agregar = new RespuestaBDanio();
			agregar.setAnio(sumaAnio.toString());
			Float y = valores.getValorm()*sumaAnio+valores.getValorb();
			agregar.setTotal((float) Math.round(y));
			respuestabdProyeccion.add(agregar);
			
		}
		
		return respuestabdProyeccion;
	}
	
	public RespuestaMinimos valoresMinimos(List<CalculoMinimosCuadrados> calculos)
	{
		RespuestaMinimos respuesta = new RespuestaMinimos();
		Float sumax= (float) 0.00;
		Float sumay=(float) 0.00;
		Float sumaxy=(float) 0.00;
		int sumax2=0;
		
		for(CalculoMinimosCuadrados cal: calculos)
		{
			
			sumax+=cal.getX();
			sumay+=cal.getY();
			sumaxy+=cal.getXy();
			sumax2+=cal.getX2();
			
		}
		
		Integer n = calculos.size();
		Float m = (float) (((n*sumaxy)-(sumax*sumay))/((n*sumax2)-(sumax*sumax)));
		Float b = (float) (((sumay*sumax2)-(sumax*sumaxy))/((n*sumax2)-(sumax*sumax)));
		respuesta.setValorm(m);
		respuesta.setValorb(b);
		return respuesta;
	}
}
