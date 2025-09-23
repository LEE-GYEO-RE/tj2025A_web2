import { createSlice } from "@reduxjs/toolkit"
import { login } from "../../userSlice";

const initialState = { isAuthenticated : false }

const userSlice = createSlice( {
    name : 'user' , 
    initialState , 
    reducers : {
        login : (state) => { state.isAuthenticated = true } ,
        logout : (state) => { state.isAuthenticated = false }
    }

});

export default userSlice.reducer
export const { login , logout } = userSlice.actions;