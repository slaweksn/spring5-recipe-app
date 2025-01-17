package guru.springframework.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
//import lombok.EqualsAndHashCode;

@Data
@Entity
public class Recipe {

	//@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	@Lob
	private String directions;
	// todo add
	// private Difficulty difficulty;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();
	
	@Lob
	private Byte[] image;

	@Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Notes notes;

	@ManyToMany
    @JoinTable(name = "recipe_category",
        joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();
	
	public Recipe addIngredient(Ingredient ingredient) {
		this.ingredients.add(ingredient);
		ingredient.setRecipe(this);
		return this;
	}
	
	public void setNotes(Notes notes) {
		if(this.notes != null) {
			this.notes.setRecipe(null);
		}
		this.notes = notes;
		this.notes.setRecipe(this);
	}
	
}
