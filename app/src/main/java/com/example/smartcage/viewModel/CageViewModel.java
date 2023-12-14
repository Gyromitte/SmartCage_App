package com.example.smartcage.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartcage.Models.Cage;
import com.example.smartcage.repository.CageRepository;

import java.util.List;

public class CageViewModel extends ViewModel {
    private CageRepository cageRepository;
    private MutableLiveData<List<Cage>> cagesLiveData = new MutableLiveData<>();
    public CageViewModel(CageRepository cageRepository) {
        this.cageRepository = cageRepository;
    }
    public CageViewModel() {
    }
    public void init(CageRepository cageRepository) {
        this.cageRepository = cageRepository;
    }
    public LiveData<List<Cage>> getCages() {
        return cagesLiveData;
    }
    public void loadCages() {
        cageRepository.getCages().observeForever(cages -> cagesLiveData.setValue(cages));
    }
}
