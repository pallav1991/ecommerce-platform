import { createFeatureSelector, createSelector } from "@ngrx/store";

export const FEATURE_NAME = 'counter';
export const selectCounterFeature = createFeatureSelector<Number>(FEATURE_NAME);
export const selectCounter = createSelector(selectCounterFeature, (state) => state);