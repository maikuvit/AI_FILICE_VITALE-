package com.example.ai_filicevitale;

import com.example.ai_filicevitale.Model.Mossa;
import com.example.ai_filicevitale.Model.Tessera;

import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.IllegalAnnotationException;
import it.unical.mat.embasp.languages.ObjectNotValidException;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;

public class MainClass {

	public MainClass() {}
	
	
	private static String encodingResource="encodings/IA";
	
	private static Handler handler;
	
	public static void main(String[] args) {
		//Creazione dell'oggetto handler che si occuper� di gestire l'invocazione 
		//del sistema ASP da utilizzare
		
		
		handler = new DesktopHandler(new DLV2DesktopService("lib/dlv-2.exe"));

		//Specifichiamo i fatti in input
		try {
			ASPMapper.getInstance().registerClass(Mossa.class);
			ASPMapper.getInstance().registerClass(Tessera.class);
		} catch (ObjectNotValidException | IllegalAnnotationException e1) {
			e1.printStackTrace();
		}
		
		
		InputProgram facts= new ASPInputProgram();
		/*facts.addObjectInput(oggetto)*/
		
		//Aggiungiamo all'handler i fatti 
		handler.addProgram(facts);
		
		
		//Specifichiamo il programma logico tramite file 
		InputProgram encoding= new ASPInputProgram();
		encoding.addFilesPath(encodingResource);
		
		//Aggiungiamo all'handler il programma logico
		handler.addProgram(encoding);
		
		
		
		//L'handler invoca DLV2 in modo SINCRONO dando come input il programma logico e i fatti
		Output output =  handler.startSync();
		
		//Analizziamo l'answer 
		AnswerSets answersets = (AnswerSets) output;
		
		//to do: togliere le carte dal tabellone chiamando il metodo
		
		
		
	}
	

}
