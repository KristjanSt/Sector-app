import { TextField } from "@mui/material";
import { useTranslation } from "react-i18next";

// eslint-disable-next-line no-unused-vars
export default function NameField({ form }) {
  const { t } = useTranslation();

  return (
    <form.Field
      name="name"
      validators={{
        onChangeAsyncDebounceMs: 500,
        onChangeAsync: ({ value }) => {
          if (value.length === 0) return t("form.name.required");
          if (value.length >= 64) return t("form.name.tooLong");
        },
      }}
      children={({ state, handleChange }) => (
        <TextField
          variant="outlined"
          value={state.value}
          onChange={(e) => handleChange(e.target.value)}
          label={t("form.name.label")}
          error={state.meta.errors.length > 0}
          helperText={state.meta.errors[0] || ""}
        />
      )}
    />
  );
}
