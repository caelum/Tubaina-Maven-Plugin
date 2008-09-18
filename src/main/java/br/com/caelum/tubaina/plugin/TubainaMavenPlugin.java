/**
 * 
 */
package br.com.caelum.tubaina.plugin;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import br.com.caelum.tubaina.ParseTypes;
import br.com.caelum.tubaina.TubainaBuilder;
import br.com.caelum.tubaina.TubainaException;

/**
 * @author jonasabreu
 * 
 * @goal build
 */
public class TubainaMavenPlugin extends AbstractMojo {

	private static final Logger LOG = Logger.getLogger(TubainaMavenPlugin.class);

	/**
	 * @parameter
	 * @required
	 */
	private String bookName;
	/**
	 * @parameter
	 * @required
	 */
	private String format;
	/**
	 * @parameter
	 */
	private String inputDir;
	/**
	 * @parameter
	 */
	private String outputDir;
	/**
	 * @parameter
	 */
	private String templateDir;
	/**
	 * @parameter
	 */
	private String outputFileName;
	/**
	 * @parameter
	 */
	private boolean listTodos;
	/**
	 * @parameter
	 */
	private boolean strictXhtml;
	/**
	 * @parameter
	 */
	private boolean showNotes;
	/**
	 * @parameter
	 */
	private boolean dontCare;
	/**
	 * @parameter
	 */
	private boolean noAnswer;

	public void execute() throws MojoExecutionException {
		LOG.debug("Parameters: [" + bookName + " " + format + " " + inputDir + " " + outputDir + " " + templateDir
				+ " " + outputFileName + " " + listTodos + " " + strictXhtml + " " + showNotes + " " + dontCare + " "
				+ noAnswer + "]");
		TubainaBuilder builder = new TubainaBuilder(ParseTypes.valueOf(format.toUpperCase()), bookName);
		if (inputDir != null) {
			builder.setInputDir(new File(inputDir));
		}
		if (outputDir != null) {
			builder.setOutputDir(new File(outputDir));
		}
		if (templateDir != null) {
			builder.setTemplateDir(new File(templateDir));
		}
		if (outputFileName != null) {
			builder.setOutputFileName(outputFileName);
		}
		if (listTodos) {
			builder.listTodos();
		}
		if (strictXhtml) {
			builder.strictXhtml();
		}
		if (showNotes) {
			builder.showNotes();
		}
		if (dontCare) {
			builder.dontCare();
		}
		if (noAnswer) {
			builder.noAnswer();
		}
		try {
			builder.build();
		} catch (IOException e) {
			throw new MojoExecutionException("Failed to generate Tubaina output.", e);
		} catch (TubainaException e) {
			throw new MojoExecutionException("Failed to generate Tubaina output.", e);
		}
	}
}
