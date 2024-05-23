package com.kkini.core.domain.recipe.service;

import com.kkini.core.domain.recipe.dto.request.RecipeRegisterRequestDto;
import org.springframework.web.multipart.MultipartFile;

public interface RecipeService {

    void removeRecipe(Long recipeId, Long memberId);

    void saveRecipe(RecipeRegisterRequestDto dto, MultipartFile multipartFile, Long memberId);
}
