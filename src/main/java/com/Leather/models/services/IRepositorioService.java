package com.Leather.models.services;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IRepositorioService {

	public Resource obtenerFoto(String nombreFoto) throws MalformedURLException;
	public String copiarfotoEnCarpeta(MultipartFile archivo) throws IOException;
	public boolean borrarFoto(String nombreFoto);
	public void borrarTodo();
	public void iniciarRepositorio() throws IOException;
}
