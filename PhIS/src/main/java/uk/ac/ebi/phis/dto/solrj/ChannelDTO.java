/*******************************************************************************
 * Copyright 2015 EMBL - European Bioinformatics Institute
 *
 * Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the
 * License.
 *******************************************************************************/
package uk.ac.ebi.phis.dto.solrj;

import org.apache.solr.client.solrj.beans.Field;

import java.util.ArrayList;
import java.util.List;


public class ChannelDTO {

	public final static String ID = "id";
	public final static String ASSOCIATED_ROI = "associated_roi";
	public final static String ASSOCIATED_IMAGE = "associated_image";
	public final static String GENE_ID = "gene_id";
	public final static String GENE_SYMBOL = "gene_symbol";
	public final static String GENETIC_FEATURE_ID = "genetic_feature_id";
	public final static String GENETIC_FEATURE_SYMBOL = "genetic_feature_symbol";
	public final static String GENETIC_FEATURE_ENSEML_ID = "genetic_feature_ensembl_id";
	public final static String MUTATION_TYPE = "mutation_type";
	public final static String CHROMOSOME = "chromosome";
	public final static String START_POS = "start_pos";
	public final static String END_POS = "end_pos";
	public final static String STRAND = "strand";
	public final static String ZYGOSITY = "zygosity";
	public final static String MARKER = "marker";
	public final static String VISUALISATION_METHOD_LABEL = "visualisation_method_label";
	public final static String VISUALISATION_METHOD_ID = "visualisation_method_id";
	public final static String VISUALISATION_METHOD_SYNONYMS = "visualisation_method_synonyms";
	public final static String VISUALISATION_METHOD_FREETEXT = "visualisation_method_freetext";

	@Field(ID)
	String id;

	@Field(ASSOCIATED_IMAGE)
	String associatedImage;
	@Field(ASSOCIATED_ROI)
	List<String> associatedRoi;
	@Field(CHROMOSOME)
	String chromosome;
	@Field(END_POS)
	long endPos;
	@Field(GENE_ID)
	String geneId;
	@Field(GENE_SYMBOL)
	String geneSymbol;
	@Field(GENETIC_FEATURE_ENSEML_ID)
	String geneticFeatureEnsemlId;
	@Field(GENETIC_FEATURE_ID)
	String geneticFeatureId;
	@Field(GENETIC_FEATURE_SYMBOL)
	String geneticFeatureSymbol;
	@Field(MUTATION_TYPE)
	String mutationType;
	@Field(MARKER)
	String marker;
	@Field(START_POS)
	long startPos;
	@Field(STRAND)
	String strand;
	@Field(ZYGOSITY)
	String zygosity;
	@Field(VISUALISATION_METHOD_ID)
	ArrayList<String> visualisationMethodId;
	@Field(ChannelDTO.VISUALISATION_METHOD_LABEL)
	ArrayList<String> visualisationMethodLabel;
	@Field(ChannelDTO.VISUALISATION_METHOD_SYNONYMS)
	ArrayList<String> visualisationMethodSynonyms;
	@Field(ChannelDTO.VISUALISATION_METHOD_FREETEXT)
	ArrayList<String> visualisationMethodFreetext;
	
	/**
	 * @return the id
	 */
	public String getId() {
	
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
	
		this.id = id;
	}
	
	
	public String getMutationType() {
	
		return mutationType;
	}


	public void setMutationType(String mutationType) {
	
		this.mutationType = mutationType;
	}

	/**
	 * @return the associatedImage
	 */
	public String getAssociatedImage() {
	
		return associatedImage;
	}
	
	/**
	 * @param associatedImage the associatedImage to set
	 */
	public void setAssociatedImage(String associatedImage) {
	
		this.associatedImage = associatedImage;
	}
	
	/**
	 * @return the associatedRoi
	 */
	public List<String> getAssociatedRoi() {
	
		return associatedRoi;
	}
	
	public void addAssociatedRoi(String roiId) {
		
		if(associatedRoi == null){
			associatedRoi = new ArrayList<>();
		}
		associatedRoi.add(roiId);
	}
	
	/**
	 * @param associatedRoi the associatedRoi to set
	 */
	public void setAssociatedRoi(List<String> associatedRoi) {
	
		this.associatedRoi = associatedRoi;
	}
	
	/**
	 * @return the chromosome
	 */
	public String getChromosome() {
	
		return chromosome;
	}
	
	/**
	 * @param chromosome the chromosome to set
	 */
	public void setChromosome(String chromosome) {
	
		this.chromosome = chromosome;
	}
	
	/**
	 * @return the endPos
	 */
	public long getEndPos() {
	
		return endPos;
	}
	
	/**
	 * @param endPos the endPos to set
	 */
	public void setEndPos(long endPos) {
	
		this.endPos = endPos;
	}
	
	/**
	 * @return the geneId
	 */
	public String getGeneId() {
	
		return geneId;
	}
	
	/**
	 * @param geneId the geneId to set
	 */
	public void setGeneId(String geneId) {
	
		this.geneId = geneId;
	}
	
	/**
	 * @return the geneSymbol
	 */
	public String getGeneSymbol() {
	
		return geneSymbol;
	}
	
	/**
	 * @param geneSymbol the geneSymbol to set
	 */
	public void setGeneSymbol(String geneSymbol) {
	
		this.geneSymbol = geneSymbol;
	}
	
	/**
	 * @return the geneticFeatureEnsemlId
	 */
	public String getGeneticFeatureEnsemlId() {
	
		return geneticFeatureEnsemlId;
	}
	
	/**
	 * @param geneticFeatureEnsemlId the geneticFeatureEnsemlId to set
	 */
	public void setGeneticFeatureEnsemlId(String geneticFeatureEnsemlId) {
	
		this.geneticFeatureEnsemlId = geneticFeatureEnsemlId;
	}
	
	/**
	 * @return the geneticFeatureId
	 */
	public String getGeneticFeatureId() {
	
		return geneticFeatureId;
	}
	
	/**
	 * @param geneticFeatureId the geneticFeatureId to set
	 */
	public void setGeneticFeatureId(String geneticFeatureId) {
	
		this.geneticFeatureId = geneticFeatureId;
	}
	
	/**
	 * @return the geneticFeatureSymbol
	 */
	public String getGeneticFeatureSymbol() {
	
		return geneticFeatureSymbol;
	}
	
	/**
	 * @param geneticFeatureSymbol the geneticFeatureSymbol to set
	 */
	public void setGeneticFeatureSymbol(String geneticFeatureSymbol) {
	
		this.geneticFeatureSymbol = geneticFeatureSymbol;
	}
	
	/**
	 * @return the marker
	 */
	public String getMarker() {
	
		return marker;
	}
	
	/**
	 * @param marker the marker to set
	 */
	public void setMarker(String marker) {
	
		this.marker = marker;
	}
	
	/**
	 * @return the startPos
	 */
	public long getStartPos() {
	
		return startPos;
	}
	
	/**
	 * @param startPos the startPos to set
	 */
	public void setStartPos(long startPos) {
	
		this.startPos = startPos;
	}
	
	/**
	 * @return the strand
	 */
	public String getStrand() {
	
		return strand;
	}
	
	/**
	 * @param strand the strand to set
	 */
	public void setStrand(String strand) {
	
		this.strand = strand;
	}
	
	/**
	 * @return the zygosity
	 */
	public String getZygosity() {
	
		return zygosity;
	}
	
	/**
	 * @param zygosity the zygosity to set
	 */
	public void setZygosity(String zygosity) {
	
		this.zygosity = zygosity;
	}

	public ArrayList<String> getVisualisationMethodSynonyms() {
		return visualisationMethodSynonyms;
	}

	public void setVisualisationMethodSynonyms(ArrayList<String> visualisationMethodSynonyms) {
		this.visualisationMethodSynonyms = visualisationMethodSynonyms;
	}
	
	public void addVisualisationMethodSynonyms(ArrayList<String> visualisationMethodSynonyms) {
		if (this.visualisationMethodSynonyms == null){
			this.visualisationMethodSynonyms = new ArrayList<>();
		}
		this.visualisationMethodSynonyms.addAll(visualisationMethodSynonyms);
	}

	public ArrayList<String> getVisualisationMethodId() {
		return visualisationMethodId;
	}

	public void setVisualisationMethodId(ArrayList<String> visualisationMethodId) {
		this.visualisationMethodId = visualisationMethodId;
	}
	public void addVisualisationMethodId(String visualisationMethodId) {
		if (this.visualisationMethodId == null){
			this.visualisationMethodId = new ArrayList<>();
		}
		this.visualisationMethodId.add(visualisationMethodId);
	}

	public ArrayList<String> getVisualisationMethodLabel() {
		return visualisationMethodLabel;
	}

	public void setVisualisationMethodLabel(ArrayList<String> visualisationMethodLabel) {
		this.visualisationMethodLabel = visualisationMethodLabel;
	}
	public void addVisualisationMethodLabel(String visualisationMethodLabel) {
		if (this.visualisationMethodLabel == null){
			this.visualisationMethodLabel = new ArrayList<>();
		}
		this.visualisationMethodLabel.add(visualisationMethodLabel);
	}

	public ArrayList<String> getVisualisationMethodFreetext() {
		return visualisationMethodFreetext;
	}

	public void setVisualisationMethodFreetext(ArrayList<String> visualisationMethodFreetext) {
		this.visualisationMethodFreetext = visualisationMethodFreetext;
	}
	public void addVisualisationMethodFreetext(String visualisationMethodFreetext) {
		if (this.visualisationMethodFreetext == null){
			this.visualisationMethodFreetext = new ArrayList<>();
		}
		this.visualisationMethodFreetext.add(visualisationMethodFreetext);
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ChannelDTO that = (ChannelDTO) o;

		if (endPos != that.endPos) return false;
		if (startPos != that.startPos) return false;
		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (associatedImage != null ? !associatedImage.equals(that.associatedImage) : that.associatedImage != null)
			return false;
		if (associatedRoi != null ? !associatedRoi.equals(that.associatedRoi) : that.associatedRoi != null)
			return false;
		if (chromosome != null ? !chromosome.equals(that.chromosome) : that.chromosome != null) return false;
		if (geneId != null ? !geneId.equals(that.geneId) : that.geneId != null) return false;
		if (geneSymbol != null ? !geneSymbol.equals(that.geneSymbol) : that.geneSymbol != null) return false;
		if (geneticFeatureEnsemlId != null ? !geneticFeatureEnsemlId.equals(that.geneticFeatureEnsemlId) : that.geneticFeatureEnsemlId != null)
			return false;
		if (geneticFeatureId != null ? !geneticFeatureId.equals(that.geneticFeatureId) : that.geneticFeatureId != null)
			return false;
		if (geneticFeatureSymbol != null ? !geneticFeatureSymbol.equals(that.geneticFeatureSymbol) : that.geneticFeatureSymbol != null)
			return false;
		if (mutationType != null ? !mutationType.equals(that.mutationType) : that.mutationType != null) return false;
		if (marker != null ? !marker.equals(that.marker) : that.marker != null) return false;
		if (strand != null ? !strand.equals(that.strand) : that.strand != null) return false;
		if (zygosity != null ? !zygosity.equals(that.zygosity) : that.zygosity != null) return false;
		if (visualisationMethodId != null ? !visualisationMethodId.equals(that.visualisationMethodId) : that.visualisationMethodId != null)
			return false;
		if (visualisationMethodLabel != null ? !visualisationMethodLabel.equals(that.visualisationMethodLabel) : that.visualisationMethodLabel != null)
			return false;
		if (visualisationMethodSynonyms != null ? !visualisationMethodSynonyms.equals(that.visualisationMethodSynonyms) : that.visualisationMethodSynonyms != null)
			return false;
		return visualisationMethodFreetext != null ? visualisationMethodFreetext.equals(that.visualisationMethodFreetext) : that.visualisationMethodFreetext == null;

	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (associatedImage != null ? associatedImage.hashCode() : 0);
		result = 31 * result + (associatedRoi != null ? associatedRoi.hashCode() : 0);
		result = 31 * result + (chromosome != null ? chromosome.hashCode() : 0);
		result = 31 * result + (int) (endPos ^ (endPos >>> 32));
		result = 31 * result + (geneId != null ? geneId.hashCode() : 0);
		result = 31 * result + (geneSymbol != null ? geneSymbol.hashCode() : 0);
		result = 31 * result + (geneticFeatureEnsemlId != null ? geneticFeatureEnsemlId.hashCode() : 0);
		result = 31 * result + (geneticFeatureId != null ? geneticFeatureId.hashCode() : 0);
		result = 31 * result + (geneticFeatureSymbol != null ? geneticFeatureSymbol.hashCode() : 0);
		result = 31 * result + (mutationType != null ? mutationType.hashCode() : 0);
		result = 31 * result + (marker != null ? marker.hashCode() : 0);
		result = 31 * result + (int) (startPos ^ (startPos >>> 32));
		result = 31 * result + (strand != null ? strand.hashCode() : 0);
		result = 31 * result + (zygosity != null ? zygosity.hashCode() : 0);
		result = 31 * result + (visualisationMethodId != null ? visualisationMethodId.hashCode() : 0);
		result = 31 * result + (visualisationMethodLabel != null ? visualisationMethodLabel.hashCode() : 0);
		result = 31 * result + (visualisationMethodSynonyms != null ? visualisationMethodSynonyms.hashCode() : 0);
		result = 31 * result + (visualisationMethodFreetext != null ? visualisationMethodFreetext.hashCode() : 0);
		return result;
	}
}
