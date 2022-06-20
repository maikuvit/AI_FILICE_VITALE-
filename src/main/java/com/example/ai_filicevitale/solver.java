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

import java.lang.reflect.InvocationTargetException;

public class solver {

	public solver() {}
	
	
	private static String encodingResource="encodings/IA";
	
	private static Handler handler;

	private static InputProgram facts = new ASPInputProgram();;


	public static void setup() {
		//Creazione dell'oggetto handler che si occuper� di gestire l'invocazione 
		//del sistema ASP da utilizzare ...
		
		handler = new DesktopHandler(new DLV2DesktopService("lib/dlv-2.exe"));

		//Specifichiamo i fatti in input
		try {
			ASPMapper.getInstance().registerClass(Mossa.class);
			ASPMapper.getInstance().registerClass(Tessera.class);
		} catch (ObjectNotValidException | IllegalAnnotationException e1) {
			e1.printStackTrace();
		}

		//Specifichiamo il programma logico tramite file
		InputProgram encoding= new ASPInputProgram();
		encoding.addFilesPath(encodingResource);

		//Aggiungiamo all'handler il programma logico
		handler.addProgram(encoding);

	}

	public static void addFact(Tessera t) throws Exception {
		facts.addObjectInput(t);
	}

	public static Mossa prossimaMossa() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {

		handler.addProgram(facts);

		//L'handler invoca DLV2 in modo SINCRONO dando come input il programma logico e i fatti
		Output output =  handler.startSync();

		//Analizziamo l'answer
		AnswerSets answersets = (AnswerSets) output;

		Mossa m = (Mossa) answersets.getOptimalAnswerSets().get(0).getAtoms();

		facts.clearAll();
		return m;
	}
	

}
