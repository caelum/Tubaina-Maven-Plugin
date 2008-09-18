/**
 * 
 */
package br.com.caelum.tubaina.plugin;

import java.io.File;
import java.io.IOException;

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
	private Boolean listTodos;
	/**
	 * @parameter
	 */
	private Boolean strictXhtml;
	/**
	 * @parameter
	 */
	private Boolean showNotes;
	/**
	 * @parameter
	 */
	private Boolean dontCare;
	/**
	 * @parameter
	 */
	private Boolean noAnswer;

	public void execute() throws MojoExecutionException {
		TubainaBuilder builder = new TubainaBuilder(ParseTypes.valueOf(format), bookName);
		builder.setInputDir(new File(inputDir)).setOutputDir(new File(outputDir)).setTemplateDir(new File(templateDir))
				.setOutputFileName(outputFileName);
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
