package com.example.event_management.service;

import com.example.event_management.dto.PresentationDTO;

public interface IPresentationService {

    PresentationDTO getPresentationbyId (long id ) ;
    PresentationDTO editPresentation(PresentationDTO editPresentation , long id);
    PresentationDTO createPresentation( PresentationDTO newPresentation);
    void deletePresentationbyId (long id) ;
}
