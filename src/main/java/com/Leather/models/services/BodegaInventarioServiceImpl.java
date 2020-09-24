package com.Leather.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Leather.models.dao.IBodegaInventarioDao;
import com.Leather.models.entity.BodegaInventario;


@Service
public class BodegaInventarioServiceImpl implements IBodegaInventarioService {
	
	@Autowired
	public IBodegaInventarioDao iBodegaInventarioDao;
	
	// Listar Bodega Inventario
	@Override
	@Transactional(readOnly = true)
	public List<BodegaInventario> ListarBodegaInventario() {
		return (List<BodegaInventario>) iBodegaInventarioDao.findAll();
	}

	// Listar Paginado Bodega Inventario
	@Override
	@Transactional(readOnly = true)
	public Page<BodegaInventario> ListarBodegaInventarioPaginado(Pageable paginador) {
		return iBodegaInventarioDao.findAll(paginador);
	}

	// Obtener Bodega Inventario Por Id
	@Override
	@Transactional(readOnly = true)
	public BodegaInventario ObtenerBodegaInventarioPorID(Long id) {
		return iBodegaInventarioDao.findById(id).orElse(null);
	}

	// Guardar Bodega Inventario
	@Override
	@Transactional
	public BodegaInventario GuardarBodegaInventario(BodegaInventario bodegaInventario) {
		return iBodegaInventarioDao.save(bodegaInventario);
	}

	// Eliminar Bodega Inventario
	@Override
	@Transactional
	public void EliminarBodegaInventario(Long id) {
		iBodegaInventarioDao.deleteById(id);
	}

}




