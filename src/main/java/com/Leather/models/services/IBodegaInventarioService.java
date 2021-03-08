package com.Leather.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Leather.models.entity.BodegaInventario;

public interface IBodegaInventarioService {
	public List<BodegaInventario> ListarBodegaInventario();
	public Page<BodegaInventario> ListarBodegaInventarioPaginado(Pageable paginador);
	public BodegaInventario ObtenerBodegaInventarioPorID(Long id);
	public BodegaInventario GuardarBodegaInventario(BodegaInventario bodegaInventario);
	public void EliminarBodegaInventario(Long id);
	public BodegaInventario BodegaInventarioPorRef(String referencia);
}
