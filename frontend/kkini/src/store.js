import { configureStore, createSlice } from '@reduxjs/toolkit';
import storage from 'redux-persist/lib/storage';
import { combineReducers } from 'redux';
import { persistReducer } from 'redux-persist';

let jwt = createSlice({
    name: 'jwt',
    initialState: { value : '' },
    reducers : {
        changeToken(state, action){
            state.value = action.payload;
        }
    }
});

const persistConfig = {
  key: 'root',
  storage,  // 로컬스토리지 사용
  whitelist: ['jwt']
};

const reducers = combineReducers({
  jwt: jwt.reducer
});

const persistedReducer = persistReducer(persistConfig, reducers);

export let { changeToken }= jwt.actions;

export default configureStore({
  reducer: persistedReducer,
  middleware: (getDefaultMiddleware) => getDefaultMiddleware({
    serializableCheck: false
  })
});
