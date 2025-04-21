import { createTheme, ThemeProvider } from "@mui/material";
import SectorForm from "./Components/SectorForm/SectorForm";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";

const customTheme = createTheme({
  palette: {
    primary: {
      main: "#0398fd",
      contrastText: "#FFFFFF", // white text and border color
    },
    secondary: {
      main: "#414141",
      light: "#9d9d9d",
    },
  },
  components: {
    MuiCheckbox: {
      defaultProps: {
        color: "primary",
      },
      styleOverrides: {
        root: {
          color: "#fff", // unchecked color
          "&.Mui-checked": {
            color: "#0398fd", // checked color
          },
        },
      },
    },
    MuiInputLabel: {
      styleOverrides: {
        root: {
          color: "#9d9d9d", // inactive label
          "&.Mui-focused": {
            color: "#FFFFFF", // focused label
          },
        },
      },
    },
    MuiOutlinedInput: {
      styleOverrides: {
        root: {
          color: "#FFFFFF", // input text color
          "& .MuiOutlinedInput-notchedOutline": {
            borderColor: "#9d9d9d", // inactive border
          },
          "&:hover .MuiOutlinedInput-notchedOutline": {
            borderColor: "#FFFFFF", // hover border
          },
          "&.Mui-focused .MuiOutlinedInput-notchedOutline": {
            borderColor: "#FFFFFF", // focused border
          },
        },
      },
    },
  },
});

const queryClient = new QueryClient();

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <ThemeProvider theme={customTheme}>
        <SectorForm />
      </ThemeProvider>
    </QueryClientProvider>
  );
}

export default App;
