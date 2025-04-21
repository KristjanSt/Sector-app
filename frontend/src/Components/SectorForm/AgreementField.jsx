import { Checkbox, FormControlLabel } from "@mui/material";
import { useTranslation } from "react-i18next";

// eslint-disable-next-line no-unused-vars
export default function AgreementField({ form }) {
  const { t } = useTranslation();

  return (
    <form.Field
      name="agreedToTerms"
      children={({ state, handleChange, handleBlur }) => (
        <FormControlLabel
          sx={{ color: "primary.contrastText" }}
          control={
            <Checkbox
              onChange={(e) => handleChange(e.target.checked)}
              onBlur={handleBlur}
              checked={state.value}
              required
            />
          }
          label={t("form.agreement.label")}
        />
      )}
    />
  );
}
