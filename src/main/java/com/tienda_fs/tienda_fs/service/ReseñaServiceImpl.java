package com.tienda_fs.tienda_fs.service;

import com.tienda_fs.tienda_fs.model.Reseña;
import com.tienda_fs.tienda_fs.repository.ReseñaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReseñaServiceImpl implements ReseñaService{

    private final ReseñaRepository reseñaRepository;

    @Autowired
    public ReseñaServiceImpl(ReseñaRepository reseñaRepository){
        this.reseñaRepository = reseñaRepository;
    }

    @Override
    public Reseña crearReseña(Reseña reseña) {
        return reseñaRepository.save(reseña);
    }

    @Override
    public Optional<Reseña> obtenerPorId(Long id){
        return reseñaRepository.buscarPorId(id);
    }

    @Override
    public List<Reseña> obtenerTodos(){
        return reseñaRepository.obtenerTodos();
    }

    @Override
    public List<Reseña> obtenerPorProducto(Long productoId) {
        return reseñaRepository.obtenerPorProducto(productoId);
    }

    @Override
    public List<Reseña> obtenerPorUsuario(Long usuarioId) {
        return reseñaRepository.obtenerPorUsuario(usuarioId);
    }

    @Override
    public Optional<Reseña> actualizarReseña(Long id, Reseña reseña){
        return reseñaRepository.buscarPorId(id).map(existing ->{
            existing.setCalificacion(reseña.getCalificacion());
            existing.setComentario(reseña.getComentario());
            existing.setUsuario(reseña.getUsuario());
            existing.setProducto(reseña.getProducto());
            return  reseñaRepository.save(existing);
        });

    }

    @Override
    public boolean eliminarReseña(Long id){
        if (reseñaRepository.existsById(id)){
            reseñaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
