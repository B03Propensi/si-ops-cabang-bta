package bta.cabang.operasional.service;

import bta.cabang.operasional.model.CutiModel;

import java.util.List;

public interface CutiService {
    void addCuti(CutiModel cuti);
    void updateCuti(CutiModel cuti);
    void deleteCuti(Long id);
    CutiModel getCutiByIdCuti(Long id);
    List<CutiModel> getAllCuti();
    List<CutiModel> getAllCutiByUser(Long idUser);
    List<CutiModel> getAllCutiByStatus(Integer status);
}
