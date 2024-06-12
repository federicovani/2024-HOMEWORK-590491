package it.uniroma3.diadia.comandi;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.Partita;

/**
 * Stampa informazioni di aiuto.
 */

public class ComandoAiuto extends AbstractComando{
	
	static final private String NOME = "aiuto";
	
	@Override
	public void esegui(Partita partita) {
		SortedSet<String> comandi = filtraClassi();
		io.mostraMessaggio("Lista comandi: ");
		for(String s : comandi)
			io.mostraMessaggio("- " + s);
	}
	
	 /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	private static Class[] getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList();
        while (resources.hasMoreElements()) {
            URL resource = (URL) resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList classes = new ArrayList();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return (Class[]) classes.toArray(new Class[classes.size()]);
    }

    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private static List findClasses(File directory, String packageName) throws ClassNotFoundException {
        List classes = new ArrayList();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
    
    private static SortedSet<String> filtraClassi() {
    	@SuppressWarnings("rawtypes")
		Class[] classes = null;
    	SortedSet<String> comandi = new TreeSet<>();
		try {
			classes = getClasses("it.uniroma3.diadia.comandi");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(Object s : classes) {
			if(s.toString().contains("class it.uniroma3.diadia.comandi.Comando") && 
					!s.toString().contains("Test") && 
					!s.toString().contains("NonValido"))
				comandi.add(s.toString().substring(40));
		}	
		return comandi;
    }

	@Override
	public String getNome() {
		return ComandoAiuto.NOME;
	}
	
	
}
