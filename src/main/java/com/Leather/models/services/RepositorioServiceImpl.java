package com.Leather.models.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class RepositorioServiceImpl implements IRepositorioService{

	// carpeta que ha sido seleccionada como repositorio de archivos
	private final static String REPOSITORIO_CARPETA = "repositorio";
	
	
	/**
	 * Este método obtiene el Path absoluto para la carpeta especificada en la variable "REPOSITORIO_CARPETA"
	 * agregando en la ruta el nombre del archivo especificado en la variable "nombreArchivo".
	 * Este método se convierte en una método auxiliar que va a ser usado por los diferentes método de esta clase.
	 * 
	 * @param String - el nombre del archivo a incluir en la ruta
	 * @return Path - La ruta absoluta al archivo 
	 * */
	public Path getPath(String nombreArchivo) {
		return Paths.get(REPOSITORIO_CARPETA).resolve(nombreArchivo).toAbsolutePath();
	}
	
	
	
	/**
	 * Este método obtiene el archivo de acuerdo a su nombre (indicado en la variable nombreFoto)
	 * @param String - el nombre del archivo que va a ser obtenido
	 * @return Resource - el archivo obtenido
	 * */
	@Override
	public Resource obtenerFoto(String nombreFoto) throws MalformedURLException {
		
		Path pathFoto = this.getPath(nombreFoto); // se genera la ruta absoluta al archivo
		Resource recurso = new UrlResource(pathFoto.toUri()); // se obtiene el recurso de acuerdo a la ruta
		
		if(!recurso.exists() || !recurso.isReadable()) {
			throw new RuntimeException("Error: no se pudo cargar la imagen: " + pathFoto.toString());
		}
		
		return recurso;
	}
	
	
	
	/**
	 * Este método copia el archivo que se indica como parámetro en la ubicación especificada
	 * @param MultipartFile - El archivo que va a ser copiado en la carpeta
	 * @return String - el nombre ÚNICO del archivo que ha sido copiado
	 **/
	@Override
	public String copiarfotoEnCarpeta(MultipartFile archivo) throws IOException {
		
		String nombreUnicoArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename(); // genero un nombre único para el archivo
		Path rootPath = this.getPath(nombreUnicoArchivo); // obtengo la ruta absoluta
		Files.copy(archivo.getInputStream(), rootPath); // creo el archivo en la ruta rootPath
		
		return nombreUnicoArchivo;
	}
	
	

	@Override
	public boolean borrarFoto(String nombreFoto) {
		
		Path rootPath = this.getPath(nombreFoto);
		File archivo = rootPath.toFile();
		
		if(archivo.exists() || archivo.canRead()) {
			archivo.delete();
			return true;
		}
		
		return false;
	}




	@Override
	public void borrarTodo() {
		if(!Files.notExists(Paths.get(REPOSITORIO_CARPETA))) {
			FileSystemUtils.deleteRecursively(Paths.get(REPOSITORIO_CARPETA).toFile());
		}
	}




	@Override
	public void iniciarRepositorio() throws IOException {
		
		if(Files.notExists(Paths.get(REPOSITORIO_CARPETA))) {
			Files.createDirectory(Paths.get(REPOSITORIO_CARPETA));
		}
		
	}
	
	
	

}
